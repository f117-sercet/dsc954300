package com.myo2o.Enum;

public enum ShopStateEnum {
	CHECK(0,""),OFFLINE(-1,""),SUCCESS(1,""),
	PASS(2,"֤ͨ"),INNER_ERROR(-1001,"�ڲ�ϵͳ����"),
	Null_SHOPID(-1002,""), Null_SHOP(-1003,"");
	
private int state;
private String stateInfo;
private ShopStateEnum(int state ,String stateInfo){
	this.state=state;
	this.stateInfo=stateInfo;
	
	}
/**
 * ������Ӧ��ֵ
 */
public static ShopStateEnum stateOf(int state) {
	for(ShopStateEnum stateEnum:values()) {
		if( stateEnum.getState()==state) {
			return stateEnum;
		}
	}

return null;

}
public int getState() {
	return state;
}

public String getStateInfo() {
	return stateInfo;
}

}
