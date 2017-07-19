package com.gaia.member.gaiatt.gaiaclinic.fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.gaia.member.gaiatt.GaiaCustomApplication;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.gaiaclinic.activity.GaiaDetailActivity;
import com.gaia.member.gaiatt.utils.AMapUtils;
import com.gaia.member.gaiatt.utils.AMapUtils.OnAMapLocationListener;
import com.gaia.member.gaiatt.utils.maputils.MyPoiOverlay;
import com.gaia.member.gaiatt.utils.maputils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: AroundGaiaMapFragment
 * @Package com.gaia.member.gaiatt.gaiaclinic.fragment
 * @Description: shenb盖亚地图fragment
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class AroundGaiaMapFragment extends Fragment implements AMap.OnMarkerDragListener{

    //底部诊所名
    @Bind(R.id.item_title)
    TextView itemTitle;
    //地址
    @Bind(R.id.item_ads)
    TextView itemAds;
    //距离
    @Bind(R.id.item_distance)
    TextView itemDistance;
    //线性布局
    @Bind(R.id.ll_amap_gaia)
    LinearLayout llAmapGaia;

    //Fragment视图
    private View view;

    //地图控件
    private MapView mapView;
    //底部布局
    private RelativeLayout amapLocal;
    //高德地图类
    private AMap aMap;

    //是否第一次定位
    private boolean isFirst = true;
    //定位数据信息
    private AMapLocation lp;
    // Poi查询条件类
    private PoiSearch.Query query;
    //设置地图搜索信息标志类
    private MyPoiOverlay poiOverlay;
    //当前Marker
    private Marker currentMarker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.amap_view_layout, container, false);
        ButterKnife.bind(this, view);
        /*
        * 设置离线地图存储目录，在下载离线地图或初始化地图设置;
         * 使用过程中可自行设置, 若自行设置了离线地图存储的路径，
         * 则需要在离线地图下载和使用地图页面都进行路径设置
         * */
        //Demo中为了其他界面可以使用下载的离线地图，使用默认位置存储，屏蔽了自定义设置
        //  MapsInitializer.sdcardDir =OffLineMapUtils.getSdCacheDir(this);
        mapView = (MapView) view.findViewById(R.id.amapView);
        amapLocal = (RelativeLayout) view.findViewById(R.id.amap_amapLocal);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();
        return view;
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.setMyLocationEnabled(true);
        }
        aMap.setOnMapClickListener(mOnMapClickListener);
        aMap.setOnMarkerClickListener(mOnMarkerClickListener);
        aMap.setOnMarkerDragListener(this);// 设置marker可拖拽事件监听器
        amapLocal.setOnClickListener(mOnClickListener);
        AMapUtils.setOnAMapLocationListener(GaiaCustomApplication.mLocationClient, mOnAMapLocationListener);//定位工具
    }

    /**
     * 声明定位回调监听器
     * */
    OnAMapLocationListener   mOnAMapLocationListener =new OnAMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            setMapLocation(amapLocation);
        }
    };

    /**
     * 设置地图定位信息
     * 进行搜索
     * */
    private void setMapLocation( AMapLocation amapLocation) {
            if (isFirst) {
                isFirst = false;
                lp = amapLocation;
                setPoiSearch(amapLocation);//获取信息开始搜索
            }
     }

    /**
     * 地图搜索
     * */
    private void setPoiSearch(AMapLocation latLng) {
        query = new PoiSearch.Query("ATM", "金融保险服务|餐饮服务|医疗保健服务", latLng.getCityCode());
        // keyWord表示搜索字符串，第二个参数表示POI搜索类型，默认为：生活服务、餐饮服务、商务住宅
        //共分为以下20种：汽车服务|汽车销售|
        //汽车维修|摩托车服务|餐饮服务|购物服务|生活服务|体育休闲服务|医疗保健服务|
        //住宿服务|风景名胜|商务住宅|政府机构及社会团体|科教文化服务|交通设施服务|
        //金融保险服务|公司企业|道路附属设施|地名地址信息|公共设施
        //cityCode表示POI搜索区域，（这里可以传空字符串，空字符串代表全国在全国范围内进行搜索）
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(1);//设置查第一页
        PoiSearch poiSearch = new PoiSearch(getActivity(), query);
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latLng.getLatitude(),
                latLng.getLongitude()), 1000));//设置周边搜索的中心点以及区域
        poiSearch.setOnPoiSearchListener(onPoiSearchListener);//设置数据返回的监听器
        poiSearch.searchPOIAsyn();//开始搜索
    }

    PoiSearch.OnPoiSearchListener onPoiSearchListener = new PoiSearch.OnPoiSearchListener() {
        @Override
        public void onPoiSearched(PoiResult result, int i) {

            if (i == 1000) {
                if (result != null && result.getQuery() != null) {// 搜索poi的结果
                    if (result.getQuery().equals(query)) {// 是否是同一条
                        PoiResult poiResult = result;
                        ArrayList<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                        List<SuggestionCity> suggestionCities = poiResult
                                .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                        if (poiItems != null && poiItems.size() > 0) {
                            //清理之前搜索结果的marker
                            if (poiOverlay != null) {
                                poiOverlay.removeFromMap();
                            }
                            setLocal(poiItems);
                            return;
                        } else if (suggestionCities != null
                                && suggestionCities.size() > 0) {
                            showSuggestCity(suggestionCities);
                        } else {
                            ToastUtil.show(getActivity(),
                                    R.string.no_result);
                        }
                        setLocal(poiItems);
                    }
                } else {
                    ToastUtil
                            .show(getActivity(), R.string.no_result);
                }
            }

        }

        @Override
        public void onPoiItemSearched(PoiItem poiItem, int i) {


        }
    };

    private void setLocal(ArrayList<PoiItem> poiItems) {
        aMap.clear();
        poiOverlay = new MyPoiOverlay(getActivity(), aMap, poiItems);
        poiOverlay.addToMap();
        poiOverlay.zoomToSpan();
        setRange();//设置范围
        SetDistance(poiItems);//计算距离

    }

    /**
     * 设置范围
     * */
    private void setRange() {
        //设置中心
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(lp.getLatitude(), lp
                .getLongitude())));
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable
                .icon_map_red)));
        markerOption.position(new LatLng(lp.getLatitude(), lp.getLongitude()));
        markerOption.draggable(true);
        aMap.addMarker(markerOption);
        //设置覆盖数据
        aMap.addCircle(new CircleOptions()
                .center(new LatLng(lp.getLatitude(),
                        lp.getLongitude())).radius(1000)
                .strokeColor(getResources().getColor(R.color.blue))
                .fillColor(Color.argb(50, 1, 1, 1))
                .strokeWidth(1));
        llAmapGaia.setVisibility(View.VISIBLE);

    }

    /**
     * 获取查询地点离定位点的距离
     * @param poiItems
     * */
    int minIndex;
    private void SetDistance(ArrayList<PoiItem> poiItems) {
        if (poiItems != null) {
            double minDistance = poiItems.get(0).getDistance();
            for (int i = 0; i < poiItems.size(); i++) {
                if (poiItems.get(i).getDistance() < minDistance) {
                    minIndex = i;
                }
            }
        }
        itemTitle.setText(poiItems.get(minIndex).getTitle());
        itemAds.setText(poiItems.get(minIndex).getSnippet());
        itemDistance.setText(poiItems.get(minIndex).getDistance() + "m");
    }

    /**
     * poi没有搜索到数据，返回一些推荐城市的信息
     */
    private void showSuggestCity(List<SuggestionCity> cities) {
        String infomation = "推荐城市\n";
        for (int i = 0; i < cities.size(); i++) {
            infomation += "城市名称:" + cities.get(i).getCityName() + "城市区号:"
                    + cities.get(i).getCityCode() + "城市编码:"
                    + cities.get(i).getAdCode() + "\n";
        }
        ToastUtil.show(getActivity(), infomation);

    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), GaiaDetailActivity.class);
            startActivity(intent);
        }
    };


    // 点击非marker区域，将显示的InfoWindow隐藏
    AMap.OnMapClickListener mOnMapClickListener = new AMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            if (currentMarker != null) {
                currentMarker.hideInfoWindow();
            }
        }
    };

    /**
     * 监听标志点击事件设置点击信息
     * */

    AMap.OnMarkerClickListener mOnMarkerClickListener = new AMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            currentMarker = marker;
            LatLng startLatlng = new LatLng(lp.getLatitude(), lp.getLongitude());
            LatLng endLatlng = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
            // 计算量坐标点距离
            int distance= (int) com.amap.api.maps.AMapUtils.calculateLineDistance(startLatlng, endLatlng);
            if (distance != 0) {
                itemTitle.setText(marker.getTitle());
                itemAds.setText(marker.getSnippet());
                itemDistance.setText(distance+"m");
            }
            return true;//false 显示弹窗 true不显示弹窗
        }
    };
    AMap.OnInfoWindowClickListener mOnInfoWindowClickListener = new AMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {

        }
    };

    /**
     * 自定义弹窗布局
     */
    AMap.InfoWindowAdapter mInfoWindowAdapter = new AMap.InfoWindowAdapter() {
        @Override
        public View getInfoWindow(Marker marker) {
           /* View infoWindow = getActivity().getLayoutInflater().inflate(
                    R.layout.custom_info_window, null);
            render(marker, infoWindow);
            return infoWindow;*/
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    };

    /**
     * 自定义infowinfow窗口，动态修改内容的
     */
    public void render(Marker marker, View view) {

    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 监听开始拖动marker事件回调
     */
    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    /**
     * 监听拖动marker时事件回调
     */
    @Override
    public void onMarkerDrag(Marker marker) {

    }

    /**
     * 监听拖动marker结束事件回调
     */
    @Override
    public void onMarkerDragEnd(Marker marker) {
        lp.setLatitude(marker.getPosition().latitude);
        lp.setLongitude(marker.getPosition().longitude);
        aMap.clear();
        setRange();//设置范围
        setPoiSearch(lp);//搜索
    }
}
