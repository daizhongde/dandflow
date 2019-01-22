function getPageCharset(){
    var charSet = "";
    var oType = getBrowser();
    switch(oType){
        case "IE":
            charSet = document.charset;
            break;
        case "FIREFOX":
            charSet = document.characterSet;
            break;
        default:
            charSet = document.characterSet;
            break;
    }
    return charSet;
}

function getPageLanguage(){
    var lang= "";
    var oType = getBrowser();
    switch(oType){
        case "IE":
            lang = window.navigator.systemLanguage;
            break;
        case "FIREFOX":
            lang = window.navigator.language;
            break;
        default:
            lang = window.navigator.language;
            break;
    }
    return lang;
} 

function getBrowser(){
    var oType = "";
    if(navigator.userAgent.indexOf("MSIE")!=-1){
        oType="IE";
    }else if(navigator.userAgent.indexOf("Firefox")!=-1){
        oType="FIREFOX";
    }
    return oType;
}