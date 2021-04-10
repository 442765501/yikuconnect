package com.yiku.dao.entity.smvoucherType;

import java.math.BigDecimal;

public class SMVoucherTypeBean {
    private Integer id;

    private String code;

    private String name;

    private String modulename;

    private String auditmodule;

    private Byte isautofillbreakpoint;

    private Byte iseditable;

    private Byte isneedaudit;

    private String prefixion1;

    private Integer prefixion1length;

    private String prefixion1content;

    private Byte iscreatorderbyprefixion1;

    private String prefixion2;

    private String prefixion2content;

    private Integer prefixion2length;

    private Byte iscreatorderbyprefixion2;

    private String prefixion3;

    private String prefixion3content;

    private Integer prefixion3length;

    private Byte iscreatorderbyprefixion3;

    private String separator;

    private Integer serialnolength;

    private BigDecimal startordenalnumber;

    private Byte disabled;

    private Byte issystem;

    private Byte iscodingvisible;

    private Byte isaudingvisible;

    private String vouchertypealias;

    private Byte isautocreate;

    private Integer displayorder;

    private String dtoname;

    private String vouchername;

    private String datatype;

    private Byte ismessagecentervisible;

    private Byte isvouchersearchvisible;

    private Integer prefixion1intercepttype;

    private Integer prefixion2intercepttype;

    private Integer prefixion3intercepttype;

    private Byte isneedworkflow;

    private Integer codemanner;

    private Integer isprefix1readonly;

    private String syspropfields;

    private String expressionname;

    private Byte isauditprint;

    private Byte isenablewfemail;

    private Byte isneedgzqaudit;

    private Byte iscreditcontrol;

    private String voucherdatebasis;

    private Byte ishighestpricecontrol;

    private Byte islowestpricecontrol;

    private Byte isoutsourcepricecontrol;

    private byte[] ts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename == null ? null : modulename.trim();
    }

    public String getAuditmodule() {
        return auditmodule;
    }

    public void setAuditmodule(String auditmodule) {
        this.auditmodule = auditmodule == null ? null : auditmodule.trim();
    }

    public Byte getIsautofillbreakpoint() {
        return isautofillbreakpoint;
    }

    public void setIsautofillbreakpoint(Byte isautofillbreakpoint) {
        this.isautofillbreakpoint = isautofillbreakpoint;
    }

    public Byte getIseditable() {
        return iseditable;
    }

    public void setIseditable(Byte iseditable) {
        this.iseditable = iseditable;
    }

    public Byte getIsneedaudit() {
        return isneedaudit;
    }

    public void setIsneedaudit(Byte isneedaudit) {
        this.isneedaudit = isneedaudit;
    }

    public String getPrefixion1() {
        return prefixion1;
    }

    public void setPrefixion1(String prefixion1) {
        this.prefixion1 = prefixion1 == null ? null : prefixion1.trim();
    }

    public Integer getPrefixion1length() {
        return prefixion1length;
    }

    public void setPrefixion1length(Integer prefixion1length) {
        this.prefixion1length = prefixion1length;
    }

    public String getPrefixion1content() {
        return prefixion1content;
    }

    public void setPrefixion1content(String prefixion1content) {
        this.prefixion1content = prefixion1content == null ? null : prefixion1content.trim();
    }

    public Byte getIscreatorderbyprefixion1() {
        return iscreatorderbyprefixion1;
    }

    public void setIscreatorderbyprefixion1(Byte iscreatorderbyprefixion1) {
        this.iscreatorderbyprefixion1 = iscreatorderbyprefixion1;
    }

    public String getPrefixion2() {
        return prefixion2;
    }

    public void setPrefixion2(String prefixion2) {
        this.prefixion2 = prefixion2 == null ? null : prefixion2.trim();
    }

    public String getPrefixion2content() {
        return prefixion2content;
    }

    public void setPrefixion2content(String prefixion2content) {
        this.prefixion2content = prefixion2content == null ? null : prefixion2content.trim();
    }

    public Integer getPrefixion2length() {
        return prefixion2length;
    }

    public void setPrefixion2length(Integer prefixion2length) {
        this.prefixion2length = prefixion2length;
    }

    public Byte getIscreatorderbyprefixion2() {
        return iscreatorderbyprefixion2;
    }

    public void setIscreatorderbyprefixion2(Byte iscreatorderbyprefixion2) {
        this.iscreatorderbyprefixion2 = iscreatorderbyprefixion2;
    }

    public String getPrefixion3() {
        return prefixion3;
    }

    public void setPrefixion3(String prefixion3) {
        this.prefixion3 = prefixion3 == null ? null : prefixion3.trim();
    }

    public String getPrefixion3content() {
        return prefixion3content;
    }

    public void setPrefixion3content(String prefixion3content) {
        this.prefixion3content = prefixion3content == null ? null : prefixion3content.trim();
    }

    public Integer getPrefixion3length() {
        return prefixion3length;
    }

    public void setPrefixion3length(Integer prefixion3length) {
        this.prefixion3length = prefixion3length;
    }

    public Byte getIscreatorderbyprefixion3() {
        return iscreatorderbyprefixion3;
    }

    public void setIscreatorderbyprefixion3(Byte iscreatorderbyprefixion3) {
        this.iscreatorderbyprefixion3 = iscreatorderbyprefixion3;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator == null ? null : separator.trim();
    }

    public Integer getSerialnolength() {
        return serialnolength;
    }

    public void setSerialnolength(Integer serialnolength) {
        this.serialnolength = serialnolength;
    }

    public BigDecimal getStartordenalnumber() {
        return startordenalnumber;
    }

    public void setStartordenalnumber(BigDecimal startordenalnumber) {
        this.startordenalnumber = startordenalnumber;
    }

    public Byte getDisabled() {
        return disabled;
    }

    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
    }

    public Byte getIssystem() {
        return issystem;
    }

    public void setIssystem(Byte issystem) {
        this.issystem = issystem;
    }

    public Byte getIscodingvisible() {
        return iscodingvisible;
    }

    public void setIscodingvisible(Byte iscodingvisible) {
        this.iscodingvisible = iscodingvisible;
    }

    public Byte getIsaudingvisible() {
        return isaudingvisible;
    }

    public void setIsaudingvisible(Byte isaudingvisible) {
        this.isaudingvisible = isaudingvisible;
    }

    public String getVouchertypealias() {
        return vouchertypealias;
    }

    public void setVouchertypealias(String vouchertypealias) {
        this.vouchertypealias = vouchertypealias == null ? null : vouchertypealias.trim();
    }

    public Byte getIsautocreate() {
        return isautocreate;
    }

    public void setIsautocreate(Byte isautocreate) {
        this.isautocreate = isautocreate;
    }

    public Integer getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(Integer displayorder) {
        this.displayorder = displayorder;
    }

    public String getDtoname() {
        return dtoname;
    }

    public void setDtoname(String dtoname) {
        this.dtoname = dtoname == null ? null : dtoname.trim();
    }

    public String getVouchername() {
        return vouchername;
    }

    public void setVouchername(String vouchername) {
        this.vouchername = vouchername == null ? null : vouchername.trim();
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype == null ? null : datatype.trim();
    }

    public Byte getIsmessagecentervisible() {
        return ismessagecentervisible;
    }

    public void setIsmessagecentervisible(Byte ismessagecentervisible) {
        this.ismessagecentervisible = ismessagecentervisible;
    }

    public Byte getIsvouchersearchvisible() {
        return isvouchersearchvisible;
    }

    public void setIsvouchersearchvisible(Byte isvouchersearchvisible) {
        this.isvouchersearchvisible = isvouchersearchvisible;
    }

    public Integer getPrefixion1intercepttype() {
        return prefixion1intercepttype;
    }

    public void setPrefixion1intercepttype(Integer prefixion1intercepttype) {
        this.prefixion1intercepttype = prefixion1intercepttype;
    }

    public Integer getPrefixion2intercepttype() {
        return prefixion2intercepttype;
    }

    public void setPrefixion2intercepttype(Integer prefixion2intercepttype) {
        this.prefixion2intercepttype = prefixion2intercepttype;
    }

    public Integer getPrefixion3intercepttype() {
        return prefixion3intercepttype;
    }

    public void setPrefixion3intercepttype(Integer prefixion3intercepttype) {
        this.prefixion3intercepttype = prefixion3intercepttype;
    }

    public Byte getIsneedworkflow() {
        return isneedworkflow;
    }

    public void setIsneedworkflow(Byte isneedworkflow) {
        this.isneedworkflow = isneedworkflow;
    }

    public Integer getCodemanner() {
        return codemanner;
    }

    public void setCodemanner(Integer codemanner) {
        this.codemanner = codemanner;
    }

    public Integer getIsprefix1readonly() {
        return isprefix1readonly;
    }

    public void setIsprefix1readonly(Integer isprefix1readonly) {
        this.isprefix1readonly = isprefix1readonly;
    }

    public String getSyspropfields() {
        return syspropfields;
    }

    public void setSyspropfields(String syspropfields) {
        this.syspropfields = syspropfields == null ? null : syspropfields.trim();
    }

    public String getExpressionname() {
        return expressionname;
    }

    public void setExpressionname(String expressionname) {
        this.expressionname = expressionname == null ? null : expressionname.trim();
    }

    public Byte getIsauditprint() {
        return isauditprint;
    }

    public void setIsauditprint(Byte isauditprint) {
        this.isauditprint = isauditprint;
    }

    public Byte getIsenablewfemail() {
        return isenablewfemail;
    }

    public void setIsenablewfemail(Byte isenablewfemail) {
        this.isenablewfemail = isenablewfemail;
    }

    public Byte getIsneedgzqaudit() {
        return isneedgzqaudit;
    }

    public void setIsneedgzqaudit(Byte isneedgzqaudit) {
        this.isneedgzqaudit = isneedgzqaudit;
    }

    public Byte getIscreditcontrol() {
        return iscreditcontrol;
    }

    public void setIscreditcontrol(Byte iscreditcontrol) {
        this.iscreditcontrol = iscreditcontrol;
    }

    public String getVoucherdatebasis() {
        return voucherdatebasis;
    }

    public void setVoucherdatebasis(String voucherdatebasis) {
        this.voucherdatebasis = voucherdatebasis == null ? null : voucherdatebasis.trim();
    }

    public Byte getIshighestpricecontrol() {
        return ishighestpricecontrol;
    }

    public void setIshighestpricecontrol(Byte ishighestpricecontrol) {
        this.ishighestpricecontrol = ishighestpricecontrol;
    }

    public Byte getIslowestpricecontrol() {
        return islowestpricecontrol;
    }

    public void setIslowestpricecontrol(Byte islowestpricecontrol) {
        this.islowestpricecontrol = islowestpricecontrol;
    }

    public Byte getIsoutsourcepricecontrol() {
        return isoutsourcepricecontrol;
    }

    public void setIsoutsourcepricecontrol(Byte isoutsourcepricecontrol) {
        this.isoutsourcepricecontrol = isoutsourcepricecontrol;
    }

    public byte[] getTs() {
        return ts;
    }

    public void setTs(byte[] ts) {
        this.ts = ts;
    }
}