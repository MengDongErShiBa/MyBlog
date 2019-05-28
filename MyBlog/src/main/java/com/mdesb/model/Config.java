package com.mdesb.model;

public class Config {
    private String footerabout;

    private String footercopyright;

    private String footericp;

    private String footerpoweredby;

    private String footerpoweredbyurl;

    private String websitedescription;

    private String websitename;

    private String websiteicon;

    private String websitelogo;

    private String youravatar;

    private String youremail;

    private String yourname;

    public String getFooterabout() {
        return footerabout;
    }

    public void setFooterabout(String footerabout) {
        this.footerabout = footerabout == null ? null : footerabout.trim();
    }

    public String getFootercopyright() {
        return footercopyright;
    }

    public void setFootercopyright(String footercopyright) {
        this.footercopyright = footercopyright == null ? null : footercopyright.trim();
    }

    public String getFootericp() {
        return footericp;
    }

    public void setFootericp(String footericp) {
        this.footericp = footericp == null ? null : footericp.trim();
    }

    public String getFooterpoweredby() {
        return footerpoweredby;
    }

    public void setFooterpoweredby(String footerpoweredby) {
        this.footerpoweredby = footerpoweredby == null ? null : footerpoweredby.trim();
    }

    public String getFooterpoweredbyurl() {
        return footerpoweredbyurl;
    }

    public void setFooterpoweredbyurl(String footerpoweredbyurl) {
        this.footerpoweredbyurl = footerpoweredbyurl == null ? null : footerpoweredbyurl.trim();
    }

    public String getWebsitedescription() {
        return websitedescription;
    }

    public void setWebsitedescription(String websitedescription) {
        this.websitedescription = websitedescription == null ? null : websitedescription.trim();
    }

    public String getWebsitename() {
        return websitename;
    }

    public void setWebsitename(String websitename) {
        this.websitename = websitename == null ? null : websitename.trim();
    }

    public String getWebsiteicon() {
        return websiteicon;
    }

    public void setWebsiteicon(String websiteicon) {
        this.websiteicon = websiteicon == null ? null : websiteicon.trim();
    }

    public String getWebsitelogo() {
        return websitelogo;
    }

    public void setWebsitelogo(String websitelogo) {
        this.websitelogo = websitelogo == null ? null : websitelogo.trim();
    }

    public String getYouravatar() {
        return youravatar;
    }

    public void setYouravatar(String youravatar) {
        this.youravatar = youravatar == null ? null : youravatar.trim();
    }

    public String getYouremail() {
        return youremail;
    }

    public void setYouremail(String youremail) {
        this.youremail = youremail == null ? null : youremail.trim();
    }

    public String getYourname() {
        return yourname;
    }

    public void setYourname(String yourname) {
        this.yourname = yourname == null ? null : yourname.trim();
    }

	@Override
	public String toString() {
		return "Config [footerabout=" + footerabout + ", footercopyright="
				+ footercopyright + ", footericp=" + footericp
				+ ", footerpoweredby=" + footerpoweredby
				+ ", footerpoweredbyurl=" + footerpoweredbyurl
				+ ", websitedescription=" + websitedescription
				+ ", websitename=" + websitename + ", websiteicon="
				+ websiteicon + ", websitelogo=" + websitelogo
				+ ", youravatar=" + youravatar + ", youremail=" + youremail
				+ ", yourname=" + yourname + "]";
	}
    
    
}