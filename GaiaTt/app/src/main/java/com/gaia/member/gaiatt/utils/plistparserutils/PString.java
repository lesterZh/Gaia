package com.gaia.member.gaiatt.utils.plistparserutils;


import com.gaia.member.gaiatt.utils.plistparserutils.domain.IPListSimpleObject;
import com.gaia.member.gaiatt.utils.plistparserutils.domain.PListObject;
import com.gaia.member.gaiatt.utils.plistparserutils.domain.PListObjectType;

/**
 * Represents a simple plist string element. Not to be confused with
 * {@link String}.
 */
public class PString extends PListObject implements
		IPListSimpleObject<String> {

	protected Stringer str;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8134261357175236382L;

	public PString() {
		setType(PListObjectType.STRING);
		str = new Stringer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.longevitysoft.android.xml.plist.domain.IPListSimpleObject#getValue()
	 */
	@Override
	public String getValue() {
		return this.str.getBuilder().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.longevitysoft.android.xml.plist.domain.IPListSimpleObject#setValue
	 * (java.lang.Object)
	 */
	@Override
	public void setValue(String val) {
		str.newBuilder().append(val);
	}

}