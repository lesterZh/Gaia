/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: PersonalArchivesActivity
 * @Package com.gaia.member.gaiatt.gaiaclinic.activity
 * @Description: 个人档案活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.gaiaclinic.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.BaseBean;
import com.gaia.member.androidlib.net.bean.PersonArchivesBean;
import com.gaia.member.androidlib.net.bean.PersonArchivesBean.ParamBean;
import com.gaia.member.androidlib.net.bean.PersonArchivesBean.ParamBean.OptionalBean;
import com.gaia.member.androidlib.net.bean.PersonArchivesBean.ParamBean.OptionalBean.EnvironmentBean;
import com.gaia.member.androidlib.net.bean.PersonArchivesBean.ParamBean.OptionalBean.PastMedicalHistoryBean;
import com.gaia.member.androidlib.net.bean.PersonArchivesBean.ParamBean.OptionalBean.PastMedicalHistoryBean.FamilyMedicalHistory;
import com.gaia.member.androidlib.net.bean.PersonArchivesBean.ParamBean.RequiredBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.gaiaclinic.ui.FlowRadioGroup;
import com.gaia.member.gaiatt.healthmanage.utils.LocalDataUtils;
import com.gaia.member.gaiatt.utils.AssetsFileUtil;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
import com.gaia.member.gaiatt.utils.PickerDialogUtils;
import com.gaia.member.gaiatt.utils.ResidenceUtils;
import com.gaia.member.gaiatt.utils.gsonutils.GsonTools;
import com.gaia.member.gaiatt.utils.maputils.ToastUtil;
import com.squareup.okhttp.RequestBody;

import org.json.JSONStringer;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/**
 * @ClassName: PersonalArchivesActivity
 *Description: 个人档案活动界面
 *@author android移动客户端-王浩韩
 * @date 2016/6/14 9:56
 */
public class PersonalArchivesActivity extends AppBaseActivity implements View.OnLayoutChangeListener {

    //标题
    @Bind(R.id.titlebar_tv)
    TextView titlebarTv;
    //隐藏标题右边按钮
    @Bind(R.id.titlebar_search_ll)
    LinearLayout titlebarSearchLl;
    //输入身份证
    @Bind(R.id.edt_useridcord)
    EditText edtUseridcord;
    //输入联系电话
    @Bind(R.id.edt_usercontract_phone)
    EditText edtUsercontractPhone;
    //联系人姓名
    @Bind(R.id.edt_usercontracter_name)
    EditText edtUsercontracterName;
    //联系人电话
    @Bind(R.id.edt_usercontracter_phone)
    EditText edtUsercontracterPhone;
    //城市
    @Bind(R.id.tv_usercity)
    TextView tvUsercity;
    //地区
    @Bind(R.id.tv_userarea)
    TextView tvUserarea;
    //地址
    @Bind(R.id.edt_useraddress)
    EditText edtUseraddress;
    //名族
    @Bind(R.id.tv_usernation)
    TextView tvUsernation;
    //文化程度
    @Bind(R.id.tv_usercultrue)
    TextView tvUsercultrue;
    //职业
    @Bind(R.id.tv_userprofession)
    TextView tvUserprofession;
    //保险
    @Bind(R.id.cb_employee_insurance)
    CheckBox cbEmployeeInsurance;
    @Bind(R.id.cb_resident_insurance)
    CheckBox cbResidentInsurance;
    @Bind(R.id.cb_rural_insurance)
    CheckBox cbRuralInsurance;
    @Bind(R.id.cb_poor_insurance)
    CheckBox cbPoorInsurance;
    @Bind(R.id.cb_commercial_insurance)
    CheckBox cbCommercialInsurance;
    @Bind(R.id.cb_all_free)
    CheckBox cbAllFree;
    @Bind(R.id.cb_all_own)
    CheckBox cbAllOwn;
    @Bind(R.id.cb_other)
    CheckBox cbOther;
    @Bind(R.id.edt_other_ways)
    EditText edtOtherWays;
    //工作单位
    @Bind(R.id.edt_userwork_unit)
    EditText edtUserworkUnit;
    //居住地
    @Bind(R.id.edt_userivel_address)
    EditText edtUserivelAddress;
    //性别
    @Bind(R.id.rb_man)
    RadioButton rbMan;
    @Bind(R.id.rb_women)
    RadioButton rbWomen;
    @Bind(R.id.rb_other)
    RadioButton rbOther;
    //户籍
    @Bind(R.id.rb_common_type)
    RadioButton rbCommonType;
    @Bind(R.id.rb_no_common_type)
    RadioButton rbNoCommonType;
    //血型
    @Bind(R.id.rb_user_a_type)
    RadioButton rbUserAType;
    @Bind(R.id.rb_user_b_type)
    RadioButton rbUserBType;
    @Bind(R.id.rb_user_ab_type)
    RadioButton rbUserAbType;
    @Bind(R.id.rb_user_no_type)
    RadioButton rbUserNoType;
    @Bind(R.id.rb_user_o_type)
    RadioButton rbUserOType;
    //RH血型
    @Bind(R.id.rb_user_positive)
    RadioButton rbUserPositivee;
    @Bind(R.id.rb_user_negative)
    RadioButton rbUserNegative;
    @Bind(R.id.rb_user_rh_no_type)
    RadioButton rbUserRhNoType;
    //婚姻
    @Bind(R.id.rb_user_married)
    RadioButton rbUserMarried;
    @Bind(R.id.rb_user_unmarried)
    RadioButton rbUserUnmarried;
    @Bind(R.id.rb_user_divorce)
    RadioButton rbUserDivorce;
    @Bind(R.id.rb_user_widowed)
    RadioButton rbUserWidowed;
    @Bind(R.id.rb_user_not_explain)
    RadioButton rbUserNotExplain;
    //..............................非必填
    @Bind(R.id.rb_rugd_allergy_no)
    RadioButton rbRugdAllergyNo;
    @Bind(R.id.rb_rugd_allergy_yes)
    RadioButton rbRugdAllergyYes;
    @Bind(R.id.rb_revelation_no)
    RadioButton rbRevelationNo;
    @Bind(R.id.rb_revelation_yes)
    RadioButton rbRevelationYes;
    @Bind(R.id.rb_illness_no)
    RadioButton rbIllnessNo;
    @Bind(R.id.rb_illness_yes)
    RadioButton rbIllnessYes;
    @Bind(R.id.rb_operation_no)
    RadioButton rbOperationNo;
    @Bind(R.id.rb_operation_yes)
    RadioButton rbOperationYes;
    @Bind(R.id.rb_trauma_no)
    RadioButton rbTraumaNo;
    @Bind(R.id.rb_trauma_yes)
    RadioButton rbTraumaYes;
    @Bind(R.id.rb_blood_transfusion_no)
    RadioButton rbBloodTransfusionNo;
    @Bind(R.id.rb_blood_transfusion_yes)
    RadioButton rbBloodTransfusionYes;
    @Bind(R.id.rb_illness_father_no)
    RadioButton rbIllnessFatherNo;
    @Bind(R.id.rb_illness_father_yes)
    RadioButton rbIllnessFatherYes;
    @Bind(R.id.rb_illness_mother_no)
    RadioButton rbIllnessMotherNo;
    @Bind(R.id.rb_illness_mother_yes)
    RadioButton rbIllnessMotherYes;
    @Bind(R.id.rb_illness_brothers_and_sisters_no)
    RadioButton rbIllnessBrothersAndSistersNo;
    @Bind(R.id.rb_illness_brothers_and_sisters_yes)
    RadioButton rbIllnessBrothersAndSistersYes;
    @Bind(R.id.rb_illness_children_no)
    RadioButton rbIllnessChildrenNo;
    @Bind(R.id.rb_illness_children_yes)
    RadioButton rbIllnessChildrenYes;
    @Bind(R.id.rb_kitchen_facilities_no)
    RadioButton rbKitchenFacilitiesNo;
    @Bind(R.id.rb_hoods)
    RadioButton rbHoods;
    @Bind(R.id.rb_ventilator)
    RadioButton rbVentilator;
    @Bind(R.id.rb_chimney)
    RadioButton rbChimney;
    @Bind(R.id.rb_liquefied)
    RadioButton rbLiquefied;
    @Bind(R.id.rb_gas)
    RadioButton rbGas;
    @Bind(R.id.rb_methane)
    RadioButton rbMethane;
    @Bind(R.id.rb_coal)
    RadioButton rbCoal;
    @Bind(R.id.rb_firewood)
    RadioButton rbFirewood;
    @Bind(R.id.rb_fuel_other)
    RadioButton rbFuelOther;
    @Bind(R.id.rb_tap_water)
    RadioButton rbTapWater;
    @Bind(R.id.rb_purification_water)
    RadioButton rbPurificationWater;
    @Bind(R.id.rb_Wells)
    RadioButton rbWells;
    @Bind(R.id.rb_river_lake_water)
    RadioButton rbRiverLakeWater;
    @Bind(R.id.rb_pond_water)
    RadioButton rbPondWater;
    @Bind(R.id.rb_other_water)
    RadioButton rbOtherWater;
    @Bind(R.id.rb_sanitary_toile)
    RadioButton rbSanitaryToile;
    @Bind(R.id.rb_septic_toile)
    RadioButton rbSepticToile;
    @Bind(R.id.rb_closestool)
    RadioButton rbClosestool;
    @Bind(R.id.rb_pit)
    RadioButton rbPit;
    @Bind(R.id.rb_shed_toile)
    RadioButton rbShedToile;
    @Bind(R.id.rb_single_livestock)
    RadioButton rbSingleLivestock;
    @Bind(R.id.rb_outdoor_livestock)
    RadioButton rbOutdoorLivestock;
    @Bind(R.id.rb_indoor_livestock)
    RadioButton rbIndoorLivestock;
    @Bind(R.id.rg_usersex)
    RadioGroup rgUsersex;
    @Bind(R.id.rg_usertype)
    RadioGroup rgUsertype;
    @Bind(R.id.rg_userabo_type)
    FlowRadioGroup rgUseraboType;
    @Bind(R.id.rg_userrh_type)
    RadioGroup rgUserrhType;
    @Bind(R.id.rg_user_marital_status)
    FlowRadioGroup rgUserMaritalStatus;
    @Bind(R.id.rg_userrugd_allergy)
    RadioGroup rgUserrugdAllergy;
    @Bind(R.id.rg_user_revelation)
    RadioGroup rgUserRevelation;
    @Bind(R.id.rg_user_illness)
    RadioGroup rgUserIllness;
    @Bind(R.id.rg_user_operation)
    RadioGroup rgUserOperation;
    @Bind(R.id.rg_user_trauma)
    RadioGroup rgUserTrauma;
    @Bind(R.id.rg_user_blood_transfusion)
    RadioGroup rgUserBloodTransfusion;
    @Bind(R.id.rg_user_illness_father)
    RadioGroup rgUserIllnessFather;
    @Bind(R.id.rg_user_illness_mother)
    RadioGroup rgUserIllnessMother;
    @Bind(R.id.rg_user_illness_brothers_and_sisters)
    RadioGroup rgUserIllnessBrothersAndSisters;
    @Bind(R.id.rg_user_illness_children)
    RadioGroup rgUserIllnessChildren;
    @Bind(R.id.rg_userkitchen_facilities)
    RadioGroup rgUserkitchenFacilities;
    @Bind(R.id.rg_userfuel_type)
    FlowRadioGroup rgUserfuelType;
    @Bind(R.id.rg_user_water)
    FlowRadioGroup rgUserWater;
    @Bind(R.id.rg_user_toilet)
    FlowRadioGroup rgUserToilet;
    @Bind(R.id.rg_user_livestock)
    RadioGroup rgUserLivestock;
    @Bind(R.id.edt_username)
    EditText edtUsername;
    @Bind(R.id.edt_userbirthday)
    EditText edtUserbirthday;
    //Activity最外层的Layout视图
    @Bind(R.id.activity_root_view)
    RelativeLayout activityRootView;
    @Bind(R.id.tv_personal_archives_save)
    TextView tvPersonalArchivesSave;

    //用户编辑档案信息
    private RequiredBean requiredBean;//必填信息
    private OptionalBean optionalBean;//非必填信息
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;


    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_personal_archives);
        ButterKnife.bind(this);
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        titlebarTv.setText(GetResourcesUtils.getString(this, R.string.personal_archives_title));
        titlebarSearchLl.setVisibility(View.GONE);
    }

    @Override
    protected void loadData() {
        //getNetData();
    }

    //获取网络数据
    private void getNetData() {
        //获得个人档案详细内容
        //getServiceData();
        ParamBean archivesParamBean = getPersonArchivesData();
        setRequired(archivesParamBean.getRequired());//必填
        setOptional(archivesParamBean.getOptional());//非必填
    }

    /**
     * 设置个人档案必填
     *
     * @param required
     */
    private void setRequired(RequiredBean required) {
        edtUsername.setText(required.getName());
        //性别
        String sex = required.getSex();
        if (sex != null && !sex.equals("")) {
            if (sex.equals(getString(R.string.user_women))) {
                rbWomen.setChecked(true);
            } else if (sex.equals(getString(R.string.user_man))) {
                rbMan.setChecked(true);
            } else {
                rbOther.setChecked(true);
            }
        }
        edtUserbirthday.setText(required.getBirthday());
        edtUseridcord.setText(required.getId() + "");
        edtUsercontractPhone.setText(required.getPhoneNumber());//联系电话
        edtUsercontracterName.setText(required.getContact());//联系人姓名
        edtUsercontracterPhone.setText(required.getContactPhoneNumber());//联系人电话
        //常住类型
        String householdType = required.getHouseholdType();
        if (householdType != null && !householdType.equals("")) {
            if (householdType.equals(getString(R.string.user_household))) {
                rbCommonType.setChecked(true);
            } else {
                rbNoCommonType.setChecked(true);
            }
        }
        //籍贯
        if (required.getNativePlace() != null) {
            String[] nativePlaces = required.getNativePlace().split("\\|");
            tvUsercity.setText(nativePlaces[0] + " " + nativePlaces[1]);//省市
            tvUserarea.setText(nativePlaces[2]);
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 3; i < nativePlaces.length; i++) {
                stringBuffer.append(nativePlaces[i] + " ");
            }
            edtUseraddress.setText(stringBuffer.toString());
        }
        tvUsernation.setText(required.getEthnicity());
        //ABO血型
        String aBOBloodType = required.getABObloodType();
        if (aBOBloodType != null && !aBOBloodType.equals("")) {
            if (aBOBloodType.equals(getString(R.string.a_type))) {
                rbUserAType.setChecked(true);
            } else if (aBOBloodType.equals(getString(R.string.b_type))) {
                rbUserBType.setChecked(true);
            } else if (aBOBloodType.equals(getString(R.string.o_type))) {
                rbUserOType.setChecked(true);
            } else if (aBOBloodType.equals(getString(R.string.ab_type))) {
                rbUserAbType.setChecked(true);
            } else {
                rbUserNoType.setChecked(true);
            }
        }
        //RH血型
        String rHbloodType = required.getRHbloodType();
        if (rHbloodType != null && !rHbloodType.equals("")) {
            if (aBOBloodType.equals(getString(R.string.positive_type))) {
                rbUserPositivee.setChecked(true);
            } else if (aBOBloodType.equals(getString(R.string.negative_type))) {
                rbUserNegative.setChecked(true);
            } else {
                rbUserRhNoType.setChecked(true);
            }
        }
        //文化程度
        tvUsercultrue.setText(required.getEducation());
        //职业
        tvUserprofession.setText(required.getProfession());
        //婚姻状况
        String maritalStatus = required.getMaritalStatus();
        if (maritalStatus != null && !maritalStatus.equals("")) {
            if (maritalStatus.equals(getString(R.string.user_married))) {
                rbUserMarried.setChecked(true);
            } else if (maritalStatus.equals(getString(R.string.user_unmarried))) {
                rbUserUnmarried.setChecked(true);
            } else if (maritalStatus.equals(getString(R.string.user_divorce))) {
                rbUserDivorce.setChecked(true);
            } else if (maritalStatus.equals(getString(R.string.user_widowed))) {
                rbUserWidowed.setChecked(true);
            } else {
                rbUserNotExplain.setChecked(true);
            }
        }

        //医疗费用支付方式
        String[] medicalPayments = required.getMedicalPayment().split("\\|");
        for (int i = 0; i < medicalPayments.length; i++) {
            if (medicalPayments[i].equals(getString(R.string.employee_insurance))) {
                cbEmployeeInsurance.setChecked(true);
            } else if (medicalPayments[i].equals(getString(R.string.resident_insurance))) {
                cbResidentInsurance.setChecked(true);
            } else if (medicalPayments[i].equals(getString(R.string.rural_insurance))) {
                cbRuralInsurance.setChecked(true);
            } else if (medicalPayments[i].equals(getString(R.string.poor__insurance))) {
                cbPoorInsurance.setChecked(true);
            } else if (medicalPayments[i].equals(getString(R.string.commercial_insurance))) {
                cbCommercialInsurance.setChecked(true);
            } else if (medicalPayments[i].equals(getString(R.string.all_free))) {
                cbAllFree.setChecked(true);
            } else if (medicalPayments[i].equals(getString(R.string.all_own))) {
                cbAllOwn.setChecked(true);
            } else if (medicalPayments[i].equals(getString(R.string.other))) {
                cbOther.setChecked(true);
            } else {
                edtOtherWays.setText(medicalPayments[i]);
            }
        }
    }


    /**
     * 设置个人档案非必填
     *
     * @param optional
     */
    private void setOptional(OptionalBean optional) {
        //工作单位
        edtUserworkUnit.setText(optional.getWrokPlace());
        //现住址
        if (optional.getAddress() != null) {
            String[] address = optional.getAddress().split("\\|");
            StringBuffer addressBuffer = new StringBuffer();
            for (int i = 0; i < address.length; i++) {
                if (i != (address.length - 1)) {
                    addressBuffer.append(address[i] + "-");
                } else {
                    addressBuffer.append(address[i]);
                }
            }
            edtUserivelAddress.setText(addressBuffer.toString());
        }
        //过敏史
        String drugAllergyHistory = optional.getDrugAllergyHistory();
        setRadioButton(drugAllergyHistory, rbRugdAllergyNo, rbRugdAllergyYes);
        //暴露史
        String exposureHistory = optional.getExposureHistory();
        setRadioButton(exposureHistory, rbRevelationNo, rbRevelationYes);
        //既往史
        PastMedicalHistoryBean pastMedicalHistoryBean = optional.getPastMedicalHistory();
        String disease = pastMedicalHistoryBean.getDisease();//疾病
        String surgery = pastMedicalHistoryBean.getDisease();//手术
        String trauma = pastMedicalHistoryBean.getDisease();//外伤
        String transfusion = pastMedicalHistoryBean.getDisease();//输血
        setRadioButton(disease, rbIllnessNo, rbIllnessYes);
        setRadioButton(surgery, rbOperationNo, rbOperationYes);
        setRadioButton(trauma, rbTraumaNo, rbTraumaYes);
        setRadioButton(transfusion, rbBloodTransfusionNo, rbBloodTransfusionYes);
        //家族病史
        FamilyMedicalHistory familyMedicalHistory = pastMedicalHistoryBean.getFamilyMedicalHistory();
        String father = familyMedicalHistory.getFather();
        String mother = familyMedicalHistory.getMother();
        String brotherAndSister = familyMedicalHistory.getBrothersAndSisters();
        String children = pastMedicalHistoryBean.getDisease();
        setRadioButton(father, rbIllnessFatherNo, rbIllnessFatherYes);
        setRadioButton(mother, rbIllnessMotherNo, rbIllnessMotherYes);
        setRadioButton(brotherAndSister, rbIllnessBrothersAndSistersNo, rbIllnessBrothersAndSistersYes);
        setRadioButton(children, rbIllnessChildrenNo, rbIllnessChildrenYes);

        //生活环境
        EnvironmentBean environmentBean = optional.getEnvironment();
        //厨房排风设施
        String kitchenExhaustSetting = environmentBean.getKitchenExhaustSetting();
        if (kitchenExhaustSetting != null && !kitchenExhaustSetting.equals("")) {
            if (kitchenExhaustSetting.equals(getString(R.string.chimney))) {
                rbChimney.setChecked(true);
            } else if (kitchenExhaustSetting.equals(getString(R.string.hoods))) {
                rbHoods.setChecked(true);
            } else if (kitchenExhaustSetting.equals(getString(R.string.ventilator))) {
                rbVentilator.setChecked(true);
            } else {
                rbKitchenFacilitiesNo.setChecked(true);
            }
        }
        //燃料类型
        String fuelType = environmentBean.getFuelType();
        if (fuelType != null &&! fuelType.equals("")) {
            if (fuelType.equals(getString(R.string.liquefied))) {
                rbLiquefied.setChecked(true);
            } else if (fuelType.equals(getString(R.string.gas))) {
                rbGas.setChecked(true);
            } else if (fuelType.equals(getString(R.string.methane))) {
                rbMethane.setChecked(true);
            } else if (fuelType.equals(getString(R.string.coal))) {
                rbCoal.setChecked(true);
            } else if (fuelType.equals(getString(R.string.firewood))) {
                rbFirewood.setChecked(true);
            } else {
                rbFuelOther.setChecked(true);
            }
        }
        //饮水
        String water = environmentBean.getWater();
        if (water != null && !water.equals("")) {
            if (water.equals(getString(R.string.tap_water))) {
                rbTapWater.setChecked(true);
            } else if (water.equals(getString(R.string.purification_water))) {
                rbPurificationWater.setChecked(true);
            } else if (water.equals(getString(R.string.wells))) {
                rbWells.setChecked(true);
            } else if (water.equals(getString(R.string.river_and_lake_water))) {
                rbRiverLakeWater.setChecked(true);
            } else if (water.equals(getString(R.string.pond_water))) {
                rbPondWater.setChecked(true);
            } else {
                rbOtherWater.setChecked(true);
            }
        }
        //厕所
        String toilet = environmentBean.getToilet();
        if (toilet != null && !toilet.equals("")) {
            if (toilet.equals(getString(R.string.sanitary_toile))) {
                rbSanitaryToile.setChecked(true);
            } else if (toilet.equals(getString(R.string.septic_toile))) {
                rbSepticToile.setChecked(true);
            } else if (toilet.equals(getString(R.string.closestool))) {
                rbClosestool.setChecked(true);
            } else if (toilet.equals(getString(R.string.pit))) {
                rbPit.setChecked(true);
            } else {
                rbShedToile.setChecked(true);
            }
        }
        //禽畜栏
        String corral = environmentBean.getCorral();
        if (corral != null && !corral.equals("")) {
            if (corral.equals(getString(R.string.single))) {
                rbSingleLivestock.setChecked(true);
            } else if (corral.equals(getString(R.string.indoor))) {
                rbIndoorLivestock.setChecked(true);
            } else {
                rbOutdoorLivestock.setChecked(true);
            }
        }
    }

    /**
     * 设置有无样式RadioButton
     */
    private void setRadioButton(String radioString, RadioButton mRadioButton1, RadioButton mRadioButton2) {
        if (radioString != null) {
            if (radioString.equals(getString(R.string.no_rb))) {
                mRadioButton1.setChecked(true);
            } else {
                mRadioButton2.setChecked(true);
            }
        }
    }

    private ParamBean getPersonArchivesData() {
        String archivesJsonString = AssetsFileUtil.getJsonStr(this, "archives.txt");
        PersonArchivesBean personArchivesBean = null;
        if (archivesJsonString != null) {
            personArchivesBean = GsonTools.getPersonArchivesBean(archivesJsonString);
        }
        return personArchivesBean.getParam();
    }

    /**
     * 获得个人档案详细内容
     * 网络数据
     * */
    private void getServiceData() {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("id").value("")//用户id
                    .key("name").value("")//用户姓名
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetPersonArchives(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<PersonArchivesBean>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                        }
                        @Override
                        public void onNext(PersonArchivesBean personArchivesBean) {
                            if (personArchivesBean.isSuccess()) {
                                Toast.makeText(PersonalArchivesActivity.this, "成功", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(PersonalArchivesActivity.this, "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //----------------------------------选择器
    //民族
    @OnClick(R.id.tv_usernation)
    void tvUsernation() {
        setPickerData(GetResourcesUtils.getString(this, R.string.nation_title),
                LocalDataUtils.getNativeArrayList(PersonalArchivesActivity.this),
                tvUsernation);
    }


    //文化程度
    @OnClick(R.id.tv_usercultrue)
    void tvUsercultrue() {
        setPickerData(GetResourcesUtils.getString(this, R.string.cultrue_title),
                LocalDataUtils.getCultrueArrayList(PersonalArchivesActivity.this),
                tvUsercultrue);
    }

    //职业
    @OnClick(R.id.tv_userprofession)
    void tvUserprofession() {
        setPickerData(GetResourcesUtils.getString(this, R.string.profession_title),
                LocalDataUtils.getprofessionArrayList(PersonalArchivesActivity.this),
                tvUserprofession);
    }

    //居住城市
    @OnClick(R.id.tv_usercity)
    void tvUsercity() {
        PickerDialogUtils pickerDialogUtils = new PickerDialogUtils();
        pickerDialogUtils.setProAndCityDialog(this, getString(R.string.clinic_residence), ResidenceUtils.getProData()
                , ResidenceUtils.getCityData(getString(R.string.clinic_beijing)), new PickerDialogUtils
                .OnDialogClickListener() {
            @Override
            public void okDialogClick(String text1, String text2) {
                tvUsercity.setText(text1 + " " + text2);
            }
        });
    }

    //居住区
    @OnClick(R.id.tv_userarea)
    void tvUserarea() {
        if (!TextUtils.isEmpty(tvUsercity.getText())) {
            String proStr = tvUsercity.getText().toString().split(" ")[0];//获取城市名
            String cityStr = tvUsercity.getText().toString().split(" ")[1];//获取城市名
            ResidenceUtils.getProData();
            ResidenceUtils.getCityData(proStr);
            setPickerData(GetResourcesUtils.getString(this, R.string.clinic_county),
                    ResidenceUtils.getAreaData(cityStr),
                    tvUserarea);
        } else {
            ToastUtil.show(this, getString(R.string.clinic_please_select_city));
        }
    }

    //保存
    @OnClick(R.id.tv_personal_archives_save)
    void tvPersonalArchivesSave() {
        //获取档案信息
        getBaseInfo();
        //检测必填数据是否为空
        if (checkRequired(requiredBean)) {
            //submitPersionInfo(requiredBean,optionalBean);//提交用户个人档案信息
            onBackPressed();
            ToastUtil.show(this, getString(R.string.save_success));
        }
    }

    //获取档案信息
    private void getBaseInfo() {
        //必填信息
        requiredBean = new RequiredBean();
        requiredBean.setName(edtUsername.getText().toString());//姓名
        requiredBean.setSex(getRadioGroupCheckData(rgUsersex));//性别
        requiredBean.setBirthday(edtUserbirthday.getText().toString());
        requiredBean.setId(edtUseridcord.getText().toString());
        requiredBean.setPhoneNumber(edtUsercontractPhone.getText().toString());
        requiredBean.setContact(edtUsercontracterName.getText().toString());
        requiredBean.setContactPhoneNumber(edtUsercontracterPhone.getText().toString());
        requiredBean.setHouseholdType(getRadioGroupCheckData(rgUsertype));
        if (!TextUtils.isEmpty(tvUsercity.getText())) {
            String[] proAndCitys = tvUsercity.getText().toString().split(" ");
            requiredBean.setNativePlace(proAndCitys[0] + "|" + proAndCitys[1] + "|" + tvUserarea.getText() + "|" +
                    edtUserivelAddress.getText());//籍贯
        }
        requiredBean.setEthnicity(tvUsernation.getText().toString());
        requiredBean.setABObloodType(getRadioGroupCheckData(rgUseraboType));
        requiredBean.setRHbloodType(getRadioGroupCheckData(rgUserrhType));
        requiredBean.setEducation(tvUsercultrue.getText().toString());
        requiredBean.setProfession(tvUserprofession.getText().toString());
        requiredBean.setMaritalStatus(getRadioGroupCheckData(rgUserMaritalStatus));
        requiredBean.setMedicalPayment(getCheckBoxCheckData());

        //非必填信息
        optionalBean = new OptionalBean();
        optionalBean.setWrokPlace(edtUserworkUnit.getText().toString());
        optionalBean.setAddress(edtUserivelAddress.getText().toString());
        optionalBean.setDrugAllergyHistory(getRadioGroupCheckData(rgUserrugdAllergy));
        optionalBean.setExposureHistory(getRadioGroupCheckData(rgUserRevelation));

        PastMedicalHistoryBean pastMedicalHistoryBean = new PastMedicalHistoryBean();//既往史
        pastMedicalHistoryBean.setDisease(getRadioGroupCheckData(rgUserIllness));
        pastMedicalHistoryBean.setSurgery(getRadioGroupCheckData(rgUserOperation));
        pastMedicalHistoryBean.setTrauma(getRadioGroupCheckData(rgUserTrauma));
        pastMedicalHistoryBean.setTransfusion(getRadioGroupCheckData(rgUserBloodTransfusion));
        FamilyMedicalHistory familyMedicalHistory = new FamilyMedicalHistory();//家庭病史
        familyMedicalHistory.setFather(getRadioGroupCheckData(rgUserIllnessFather));
        familyMedicalHistory.setMother(getRadioGroupCheckData(rgUserIllnessMother));
        familyMedicalHistory.setChildren(getRadioGroupCheckData(rgUserIllnessChildren));
        familyMedicalHistory.setBrothersAndSisters(getRadioGroupCheckData(rgUserIllnessBrothersAndSisters));
        pastMedicalHistoryBean.setFamilyMedicalHistory(familyMedicalHistory);
        optionalBean.setPastMedicalHistory(pastMedicalHistoryBean);

        EnvironmentBean environmentBean = new EnvironmentBean();//生活环境
        environmentBean.setKitchenExhaustSetting(getRadioGroupCheckData(rgUserkitchenFacilities));
        environmentBean.setFuelType(getRadioGroupCheckData(rgUserfuelType));
        environmentBean.setWater(getRadioGroupCheckData(rgUserWater));
        environmentBean.setToilet(getRadioGroupCheckData(rgUserToilet));
        environmentBean.setCorral(getRadioGroupCheckData(rgUserLivestock));
        optionalBean.setEnvironment(environmentBean);
    }

    /**
     * 提交用户个人档案
     * @param requiredBean 必填信息
     * @param optionalBean 非必填信息
     * */
    private void submitPersionInfo(RequiredBean requiredBean, OptionalBean optionalBean) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("required").value(requiredBean)
                    .key("optional").value(optionalBean)
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postPutArchives(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<BaseBean>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                        }
                        @Override
                        public void onNext(BaseBean baseBean) {
                            if (baseBean.isSuccess()) {
                                Toast.makeText(PersonalArchivesActivity.this, "成功", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(PersonalArchivesActivity.this, "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    //获取checkBox选择
    private String getCheckBoxCheckData() {
        StringBuffer cbStringBuffer = new StringBuffer();
        if (cbEmployeeInsurance.isChecked()) {
            cbStringBuffer.append(cbEmployeeInsurance.getText() + getString(R.string.vertical_symbol));
        } else if (cbCommercialInsurance.isChecked()) {
            cbStringBuffer.append(cbCommercialInsurance.getText() + getString(R.string.vertical_symbol));
        } else if (cbRuralInsurance.isChecked()) {
            cbStringBuffer.append(cbRuralInsurance.getText() + getString(R.string.vertical_symbol));
        } else if (cbPoorInsurance.isChecked()) {
            cbStringBuffer.append(cbPoorInsurance.getText() + getString(R.string.vertical_symbol));
        } else if (cbResidentInsurance.isChecked()) {
            cbStringBuffer.append(cbResidentInsurance.getText() + getString(R.string.vertical_symbol));
        } else if (cbAllFree.isChecked()) {
            cbStringBuffer.append(cbAllFree.getText() + getString(R.string.vertical_symbol));
        } else if (cbAllOwn.isChecked()) {
            cbStringBuffer.append(cbAllOwn.getText() + getString(R.string.vertical_symbol));
        } else if (cbOther.isChecked()) {
            cbStringBuffer.append(cbOther.getText());
        } else if (!TextUtils.isEmpty(edtOtherWays.getText())) {
            cbStringBuffer.append(getString(R.string.vertical_symbol) + edtOtherWays.getText());
        }

        return cbStringBuffer.toString();
    }

    /**
     * 获取RadioGroup的选择数据
     *
     * @param group
     */
    private String getRadioGroupCheckData(RadioGroup group) {
        String checkStr = "";
        RadioButton rbCheck = (RadioButton) findViewById(group.getCheckedRadioButtonId());
        try {
            checkStr = rbCheck.getText() + "";
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return checkStr;
    }

    //检测必填数据是否为空
    private boolean checkRequired(RequiredBean requiredBean) {
        String errorMessage = null;
        if (TextUtils.isEmpty(requiredBean.getName())) {
            errorMessage = getString(R.string.please_input_name);
        } else if (TextUtils.isEmpty(requiredBean.getSex())) {
            errorMessage = getString(R.string.please_select_sex);
        } else if (TextUtils.isEmpty(requiredBean.getBirthday())) {
            errorMessage = getString(R.string.please_input_birthday);
        } else if (TextUtils.isEmpty(requiredBean.getId())) {
            errorMessage = getString(R.string.please_input_id);
        } else if (!TextUtils.isEmpty(requiredBean.getId()) && !personIdValidation(requiredBean.getId())) {
            errorMessage = getString(R.string.please_input_right_id);//身份证验证
        } else if (TextUtils.isEmpty(requiredBean.getPhoneNumber())) {
            errorMessage = getString(R.string.please_input_phonenumber);
        } else if (!TextUtils.isEmpty(requiredBean.getPhoneNumber()) && !phoneNumberValidation(requiredBean
                .getPhoneNumber())) {
            errorMessage = getString(R.string.please_input_right_number);//手机号码验证
        } else if (TextUtils.isEmpty(requiredBean.getHouseholdType())) {
            errorMessage = getString(R.string.please_select_holdtype);
        } else if (TextUtils.isEmpty(requiredBean.getNativePlace())) {
            errorMessage = getString(R.string.please_select_native_place);
        } else if (TextUtils.isEmpty(requiredBean.getEthnicity())) {
            errorMessage = getString(R.string.please_select_etchnicity);
        } else if (TextUtils.isEmpty(requiredBean.getABObloodType())) {
            errorMessage = getString(R.string.please_select_abo);
        } else if (TextUtils.isEmpty(requiredBean.getRHbloodType())) {
            errorMessage = getString(R.string.please_select_rh);
        } else if (TextUtils.isEmpty(requiredBean.getEducation())) {
            errorMessage = getString(R.string.please_select_education);
        } else if (TextUtils.isEmpty(requiredBean.getProfession())) {
            errorMessage = getString(R.string.please_select_profession);
        } else if (TextUtils.isEmpty(requiredBean.getMaritalStatus())) {
            errorMessage = getString(R.string.please_select_marital_status);
        } else if (TextUtils.isEmpty(requiredBean.getMedicalPayment())) {
            errorMessage = getString(R.string.please_select_payment);
        } else {
            return true;
        }
        ToastUtil.show(this, errorMessage);
        return false;
    }

    /**
     * 验证手机号是否符合规则
     *
     * @param text 身份证号
     * @return
     */
    private boolean phoneNumberValidation(String text) {
        String reg1 = "[0-9]{11}";
        return text.matches(reg1);
    }

    /**
     * 验证身份证号是否符合规则
     *
     * @param text 身份证号
     * @return
     */
    public boolean personIdValidation(String text) {
        String regx = "[0-9]{17}x";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }

    /***
     * 设置选择器
     *
     * @param title     选择器标题
     * @param arrayList 选择器数据源
     * @param tv        获取选中String设置控件
     */
    private void setPickerData(String title, ArrayList<String> arrayList, final TextView tv) {
        PickerDialogUtils pickerDialogUtils = new PickerDialogUtils();
        pickerDialogUtils.setSinglePickerDialog(PersonalArchivesActivity.this, title, arrayList, 0, new
                PickerDialogUtils.OnDialogClickListener() {
                    @Override
                    public void okDialogClick(String text1, String text2) {
                        tv.setText(text1);
                    }
                });
    }

    //返回
    @OnClick(R.id.titlebar_back_ll)
    void titlebarBackLl() {
        onBackPressed();
    }


    @Override
    protected void onResume() {
        super.onResume();
        //添加layout大小发生改变监听器
        activityRootView.addOnLayoutChangeListener(this);
    }

    /**
     * 监听布局改变
     */
    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int
            oldRight, int oldBottom) {
        //old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
        // System.out.println(oldLeft + " " + oldTop +" " + oldRight + " " + oldBottom);
        // System.out.println(left + " " + top +" " + right + " " + bottom);
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        //监听到软键盘弹起...
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
            tvPersonalArchivesSave.setVisibility(View.GONE);//隐藏保存按钮
            //监听到软件盘关闭...
        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
            tvPersonalArchivesSave.setVisibility(View.VISIBLE);//显示保存按钮
        }
    }

}
