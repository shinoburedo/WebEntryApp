
//ユーザの環境をチェックする（ブラウザ、クッキーなど）
function checkUser() {
    var browserName = getBrowserName();
    var browserVersion = getBrowserVersion();
    if( browserName == 'IE' ){
        if(browserVersion<6){
            return false;
        }else{
            return checkCookie();
        }
    }else if( browserName == 'Firefox' ){
        if(browserVersion<3.5){
            return false;
        }else{
            return checkCookie();
        }
    }else{
        return false;
    }
}
function checkCookie(){
    var test;
    document.cookie = "test=true;"
    test = document.cookie.substring(document.cookie.lastIndexOf("test")+5,document.cookie.lastIndexOf("test")+9);
    if(test == "true"){
        document.cookie = "test=true; expires=Fri, 31-Dec-1999 23:59:59 GMT;";
        return true;
    }else{
        return false;
    }
}




//ブラウザ名判定
function getBrowserName(){
    var strUA = "";
    strUA = navigator.userAgent.toLowerCase();

    if(strUA.indexOf("safari") != -1){
        return "Safari";

    }else if(strUA.indexOf("firefox") != -1){
        return "Firefox";

    }else if(strUA.indexOf("opera") != -1){
        return "Opera";

    }else if(strUA.indexOf("netscape") != -1){
        return"Netscape";

    }else if(strUA.indexOf("msie") != -1){
        return "IE";
    }else{
        return "";
    }
}
//ブラウザバージョン
function getBrowserVersion()
{
    var browser = getBrowserName();
    var version = 0;
    var s = 0;
    var e = 0;
    var appVer  = navigator.appVersion;
    var uName  = navigator.userAgent.toUpperCase();
    if (browser == "Safari")
    {
        s = uName.indexOf("SAFARI/",0);
        version = (uName.substring(s+7,s+99));
        if (version < 400) version = 1;
        if ((version >= 400) < (version <= 499)) version = 2;
        if (version >= 500) version = 3;
    }
    if (browser == "Firefox")
    {
        s = uName.indexOf("FIREFOX/",0);
        version = parseFloat(uName.substring(s+8,s+8+3));
    }
    if (browser == "Opera")
    {
        s = uName.indexOf("OPERA",0) + 6;
        e = uName.indexOf(" ",s);
        version = parseFloat(uName.substring(s,e));
    }
    if (browser == "Netscape")
    {
        s = appVer.indexOf(" ",0);
        version = eval(appVer.substring(0,s));
        if (version >= 5) version++;
    }
    if (browser == "IE")
    {
        appVer  = navigator.userAgent;
        s = appVer.indexOf("MSIE ",0) + 5;
        e = appVer.indexOf(";",s);
        version = eval(appVer.substring(s,e));
    }
    return version;
}
