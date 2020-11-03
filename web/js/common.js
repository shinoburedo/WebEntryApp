function watermark(){
	
    $.updnWatermark.attachAll();
	
    //ウィンドウ変更によってウォーターマークがずれるのを制御
    $(window).resize(function(){
        $('.updnWatermark').remove();
        $.updnWatermark.attachAll();
    });

    //エラーメッセージ表示でウォーターマークがずれるのを制御
    $('td').exResize(function(){
        $('.updnWatermark').remove();
        $.updnWatermark.attachAll();
    });
};

//戻るボタン制御
window.onunload = function(){};
history.forward();


//ブラウザがIEだったらEnterキーによる送信をしなくする(
if( navigator.appName.charAt(0)=='M'){
    window.document.onkeydown = keyCheck;
}
//ブラウザがFireFoxだったらEnterキーによる送信をしなくする(
if(navigator.appName.charAt(0)=='N'){
    window.document.onkeydown=keyCheckf;
}

//Enterキーによる送信をしなくする(IE)
function keyCheck(){
    if((event.keyCode == '13') || (event.which == '13' || event.which ==  '3')){
        return false;
    }
}

//Enterキーによる送信をしなくする(FireFox)
function keyCheckf(e){
    if(e.which == '13' ||e.which ==  '3'){
        return false;
    }
}

//Enterキーによる送信をする(IE)
function keyCheck2(){
    if((event.keyCode == '13') || (event.which == '13' || event.which ==  '3')){
        return true;
    }
}

//Enterキーによる送信をする(FireFox)
function keyCheckf2(e){
    if(e.which == '13' ||e.which ==  '3'){
        return true;
    }
}

//ボタンにカーソルがあるとき呼び出される
function onenter(){
    if( navigator.appName.charAt(0)=='M'){//IEだったら
        window.document.onkeydown = keyCheck2;
    }
    if(navigator.appName.charAt(0)=='N'){//FireFoxだったら
        window.document.onkeydown=keyCheckf2;
    }
}
//ボタンにカーソルがないとき呼び出される
function offenter(){
    if( navigator.appName.charAt(0)=='M'){//IEだったら
        window.document.onkeydown = keyCheck;
    }
    if(navigator.appName.charAt(0)=='N'){//FireFoxだったら
        window.document.onkeydown=keyCheckf;
    }
}

//二度押し防止(disabled=trueにする)
function disableSubmit(form) {
    var elements = form.elements;
    for (var i = 0; i < elements.length; i++) {
        if (elements[i].type == 'submit' || elements[i].type == 'button') {
            elements[i].disabled = true;
        }
    }
}

//disabled=trueにすると、その値が送信されないため、hiddenを作成
function setHiddenValue(button) {
    if (button.name) {
        var q = document.createElement('input');
        q.type = 'hidden';
        q.name = button.name;
        q.value = button.value;
        button.form.appendChild(q);
    }
}



//URLを新規ウィンドウで開く
function openUrl(url){
    var wx = 1000;
    var wy = 700;
    var x = (screen.width - wx) / 2;
    var y = (screen.height - wy) / 2;

    var win = window.open(url, "window1","scrollbars=yes,alwaysRaised=yes,,top="+y+",left="+x+",width="+wx+",height="+wy+",resizable=yes,status=no,titlebar=yes");
    win.focus();

}

//ホイール操作を無効にする
function NoWheel(){
    if(document.activeElement.getAttribute('type')=='select-one'){
        return false;
    }else{
        return true
    }
}
function isYubinNo(y1,y2){
    if(y1 == ""){
        window.alert("郵便番号の前３桁が入力されていません。");
        return false
    }
    if(y2 == ""){
        window.alert("郵便番号の後４桁が入力されていません。");
        return false;
    }
    if(y1.length != 3){
        window.alert("郵便番号の前３桁は数字３桁で入力してください。");
        return false;
    }
    if(y2.length != 4){
        window.alert("郵便番号の後４桁は数字４桁で入力してください。");
        return false;
    }
    if(!isNumber(y1)){
        window.alert("郵便番号の前３桁は半角数字で入力してください。");
        return false;
    }
    if(!isNumber(y2)){
        window.alert("郵便番号の後４桁は半角数字で入力してください。");
        return false;
    }
    return true;
}
//半角数字か判定する
function isNumber(str)
{
    if (str.match(/[^0-9]+/) ){
        return false;
    }
    return true;
}
var subWinFlg = false;
// path  {/YubinSelAct  Actionへの相対パス}  
// ex) '../../YubinSelAct.do' 
function linkOpenWin(path){ 
    var y1 = document.forms[0].yubinNo1.value;
    var y2 = document.forms[0].yubinNo2.value;
    if(isYubinNo(y1,y2)){
        var url = path + '?yubinNo1='+y1+'&yubinNo2='+y2;
        if(subWinFlg==true){
            if(!subWin.closed){
                subWin.focus();
                subWin = window.open(url,"Yubinhenkan","_blank,top=no,left=no,menubar=no,scrollbars=yes,alwaysRaised=yes,top=50,left=0,width=800,height=250,resizable=yes,status=no,titlebar=yes");              
            }else{
                subWin = window.open(url,"Yubinhenkan","_blank,top=no,left=no,menubar=no,scrollbars=yes,alwaysRaised=yes,top=50,left=0,width=800,height=250,resizable=yes,status=no,titlebar=yes");
            }
        }else{
            subWin = window.open(url,"Yubinhenkan","_blank,top=no,left=no,menubar=no,scrollbars=yes,alwaysRaised=yes,top=50,left=0,width=800,height=250,resizable=yes,status=no,titlebar=yes");
            subWinFlg = true;
        }
    }
}


