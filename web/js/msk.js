//メニューのホイール操作を無効にする（基本情報入力）
function NoWheelInp(obj){
    if(obj==document.MskInpFrm.birthdayToshi){
        return false;
    }else if(obj==document.MskInpFrm.birthdayTsuki){
        return false;
    }else if(obj==document.MskInpFrm.birthdayHi){
        return false;
    }else if(obj==document.MskInpFrm.koshuCompletionGokakuBiToshi){
        return false;
    }else if(obj==document.MskInpFrm.koshuCompletionGokakuBiTsuki){
        return false;
    }else if(obj==document.MskInpFrm.koshuCompletionGokakuBiHi){
        return false;
    }else if(obj==document.MskInpFrm.passwdQuestionCode1){
        return false;
    }else if(obj==document.MskInpFrm.passwdQuestionCode2){
        return false;
    }else{
        return true;
    }
}

//メニューのホイール操作を無効にする（決済情報入力クレジットカード）
function NoWheelCrdInp(obj){
    if(obj==document.MskPayCrdFrm.crd_kigen_tsuki){
        return false;
    }else if(obj==document.MskPayCrdFrm.crd_kigen_toshi){
        return false;
    }else{
        return true;
    }
}

//ラジオボタン「あり」にチェックが入ったら入力可にする。(身障者希望有無)
function disabled_onclick3(){
    if(document.MskInpFrm.shinshoshaFlg[1].checked){//checked チェックが入っていたら
        document.MskInpFrm.shinshoshaHairyoNaiyou.disabled =false;// 入力可, 有効化
    }
    else{
        document.MskInpFrm.shinshoshaHairyoNaiyou.disabled =true;// 入力不可,無効化
        document.MskInpFrm.shinshoshaHairyoNaiyou.value = "";
    }
}





////郵便変換ポップアップをオープン！
//var subwin;
//function openYubin(){
//	document.getElementById('jushoTodofukenShow').innerHTML = document.MskInpFrm.jushoTodofuken.value;
//	document.getElementById('jushoShikugunShow').innerHTML = document.MskInpFrm.jushoShikugun.value;
//	var wx = 600;
//	var wy = 250;
//	var x = (screen.width - wx) / 2;
//	var y = (screen.height - wy) / 2;
//
//	if(document.MskInpFrm.yubinFlg.value=="1"){
//		var url = 'MskYubinSel.do';
//		if(!subwin || subwin.closed){
//			subwin = window.open(url, 'Yubinhenkan',"menubar=no,scrollbars=yes,alwaysRaised=yes,top="+y+",left="+x+",width="+wx+",height="+wy+",resizable=yes,status=no,titlebar=yes");
//			subwin.focus();
//		}
//		else{
//			subwin = window.open(url, 'Yubinhenkan',"menubar=no,scrollbars=yes,alwaysRaised=yes,top="+y+",left="+x+",width="+wx+",height="+wy+",resizable=yes,status=no,titlebar=yes");
//			subwin.focus();
//		}
//	}
//}




//親画面に選択した値を表示する
//function setSelectValue(todofuken,shikugun,choson,todofukencode){
//	window.opener.document.getElementById('jushoTodofukenShow').innerHTML = todofuken;//表示用の都道府県をセット
//	window.opener.document.MskInpFrm.jushoTodofuken.value=todofuken;//都道府県をセット
//	window.opener.document.getElementById('jushoShikugunShow').innerHTML = shikugun;//表示用の市区郡をセット
//	window.opener.document.MskInpFrm.jushoShikugun.value=shikugun;//市区郡をセット
//	window.opener.document.MskInpFrm.jushoChouson.value=choson;//町村をセット
//	window.opener.document.MskInpFrm.todofukenCode.value=todofukencode;//都道府県コードをセット
//
//	window.close();
//	}

//一回だけURLを新規ウィンドウで開く
function openUrlOneTime(url){
    var obj = document.myForm.flg.value;
    if(obj == 'false'){
        var wx = 1000;
        var wy = 700;
        var x = (screen.width - wx) / 2;
        var y = (screen.height - wy) / 2;

        document.myForm.flg.value = 'true';
        window.open(url, '_new',"scrollbars=yes,alwaysRaised=yes,,top="+y+",left="+x+",width="+wx+",height="+wy+",resizable=yes,status=no,titlebar=yes");
    }
}

function WinCls(){
    window.close();
}

function save(){
    var title = document.title
    window.document.execCommand("SaveAs",true,title+".html");
}

//申込区分変更時
function selectMoshikomiKbn(){
    if(document.forms[0].moshikomiKbn[1].checked) {
        document.forms[0].moshikomiDantaiCode.disabled = false;
        document.forms[0].moshikomiDantaiCode.style.backgroundColor='#FFFFFF';
        document.getElementById('dantaiNameDisp').style.display = '';
        document.forms[0].dantaiSearch.disabled = false;
    } else {
        document.forms[0].moshikomiDantaiCode.disabled = true;
        document.forms[0].moshikomiDantaiCode.style.backgroundColor='#D4D0C8';
        document.forms[0].moshikomiDantaiCode.value = '';
        document.getElementById('dantaiNameDisp').style.display = 'none';
        document.forms[0].dantaiSearch.disabled = true;
    }
}

function searchChimei(){
    if (document.forms[0].chimeiFlg.value == "1") {
        window.alert("該当する準会場が見つかりませんでした。");
    } else if (document.forms[0].chimeiFlg.value == "2") {
        window.alert("準会場コードが入力されていません。");
    } else if (document.forms[0].chimeiFlg.value == "3") {
        window.alert("準会場コードは半角数字６桁で入力してください。");
    }
    document.forms[0].chimeiFlg.value = "0";
}
function searchDantai(){
    if (document.forms[0].dantaiFlg.value == "1") {
        window.alert("該当する団体が見つかりませんでした。");
    } else if (document.forms[0].dantaiFlg.value == "2") {
        window.alert("団体コードが入力されていません。");
    } else if (document.forms[0].dantaiFlg.value == "3") {
        window.alert("団体コードは半角数字５桁で入力してください。");
    }
    document.forms[0].dantaiFlg.value = "0";
}

//免除希望変更時
function selectMenjoKiboFlg(){
    if(document.forms[0].menjoKiboFlg[1].checked) {
        document.forms[0].menjoNendoKiKyu1.disabled = false;
        document.forms[0].menjoNendoKiKyu1.style.backgroundColor="#FFFFFF";
        document.forms[0].menjoId1.disabled = false;
        document.forms[0].menjoId1.style.backgroundColor="#FFFFFF";
        document.forms[0].menjoNendoKiKyu2.disabled = false;
        document.forms[0].menjoNendoKiKyu2.style.backgroundColor="#FFFFFF";
        document.forms[0].menjoId2.disabled = false;
        document.forms[0].menjoId2.style.backgroundColor="#FFFFFF";
    } else {
        document.forms[0].menjoNendoKiKyu1.disabled = true;
        document.forms[0].menjoNendoKiKyu1.style.backgroundColor="#D4D0C8";
        document.forms[0].menjoId1.disabled = true;
        document.forms[0].menjoId1.style.backgroundColor="#D4D0C8";
        document.forms[0].menjoNendoKiKyu2.disabled = true;
        document.forms[0].menjoNendoKiKyu2.style.backgroundColor="#D4D0C8";
        document.forms[0].menjoId2.disabled = true;
        document.forms[0].menjoId2.style.backgroundColor="#D4D0C8";
        
        document.forms[0].menjoNendoKiKyu1.value = '';
        document.forms[0].menjoId1.value = '';
        document.forms[0].menjoNendoKiKyu2.value = '';
        document.forms[0].menjoId2.value = '';
    }
}

//１次受験地変更時
function chikuChange(){
    var val = document.forms[0].shikenchiCode1.value;
    if(val == '000'){
        document.getElementById('chimei').style.display = '';
        document.forms[0].junkaijoCode.disabled = false;
        document.forms[0].junkaijoCode.style.backgroundColor = '#FFFFFF';
        
        document.forms[0].chimeiSearch.disabled = false;
    } else {
        document.forms[0].junkaijoCode.disabled = true;
        document.forms[0].junkaijoCode.style.backgroundColor = "#D4D0C8";
        
        document.getElementById('chimei').style.display = 'none';
        document.forms[0].chimeiSearch.disabled = true;
        document.forms[0].junkaijoCode.value='';
    }
}

//海外の住所の方チェック時
function kaigaiChecked(){
    if(document.forms[0].kaigaiJushoFlg[1].checked){
        //        document.forms[0].yubinNo1.value = "";
        document.forms[0].yubinNo1.disabled = true;
        document.forms[0].yubinNo1.style.backgroundColor = "#D4D0C8";
        //        document.forms[0].yubinNo2.value = "";
        document.forms[0].yubinNo2.disabled = true;
        document.forms[0].yubinNo2.style.backgroundColor = "#D4D0C8";
        //        document.forms[0].jusho1.value = "";
        document.forms[0].jusho1.disabled = true;
        document.forms[0].jusho1.style.backgroundColor = "#D4D0C8";
        //        document.forms[0].jusho2.value = "";
        document.forms[0].jusho2.disabled = true;
        document.forms[0].jusho2.style.backgroundColor = "#D4D0C8";
        //        document.forms[0].jusho3.value = "";
        document.forms[0].jusho3.disabled = true;
        document.forms[0].jusho3.style.backgroundColor = "#D4D0C8";
        document.forms[0].jushoSearch.disabled = true;
        
        document.forms[0].kaigaiJusho1.disabled = false;
        document.forms[0].kaigaiJusho1.style.backgroundColor = "#FFFFFF";
        document.forms[0].kaigaiJusho2.disabled = false;
        document.forms[0].kaigaiJusho2.style.backgroundColor = "#FFFFFF";
        document.forms[0].kaigaiJusho3.disabled = false;
        document.forms[0].kaigaiJusho3.style.backgroundColor = "#FFFFFF";
    } else {
        document.forms[0].yubinNo1.disabled = false;
        document.forms[0].yubinNo1.style.backgroundColor = "#FFFFFF";
        document.forms[0].yubinNo2.disabled = false;
        document.forms[0].yubinNo2.style.backgroundColor = "#FFFFFF";
        document.forms[0].jusho1.disabled = false;
        document.forms[0].jusho1.style.backgroundColor = "#FFFFFF";
        document.forms[0].jusho2.disabled = false;
        document.forms[0].jusho2.style.backgroundColor = "#FFFFFF";
        document.forms[0].jusho3.disabled = false;
        document.forms[0].jusho3.style.backgroundColor = "#FFFFFF";
        document.forms[0].jushoSearch.disabled = false;
        
        //        document.forms[0].kaigaiJusho1.value = "";
        document.forms[0].kaigaiJusho1.disabled = true;
        document.forms[0].kaigaiJusho1.style.backgroundColor = "#D4D0C8";
        //        document.forms[0].kaigaiJusho2.value = "";
        document.forms[0].kaigaiJusho2.disabled = true;
        document.forms[0].kaigaiJusho2.style.backgroundColor = "#D4D0C8";
        //        document.forms[0].kaigaiJusho3.value = "";
        document.forms[0].kaigaiJusho3.disabled = true;
        document.forms[0].kaigaiJusho3.style.backgroundColor = "#D4D0C8";
    }
}



//郵便変換ポップアップをオープン！
var subwin;
function openYubin(){
    var wx = 600;
    var wy = 250;
    var x = (screen.width - wx) / 2;
    var y = (screen.height - wy) / 2;
    var flg = document.forms[0].yubinFlg.value;

    if(flg == "1"){
        var url = 'MskYubinSelAct.do';
        if(!subwin || subwin.closed){
            subwin = window.open(url, 'Yubinhenkan',"menubar=no,scrollbars=yes,alwaysRaised=yes,top="+y+",left="+x+",width="+wx+",height="+wy+",resizable=yes,status=no,titlebar=yes");
            subwin.focus();
        }else{
            subwin = window.open(url, 'Yubinhenkan',"menubar=no,scrollbars=yes,alwaysRaised=yes,top="+y+",left="+x+",width="+wx+",height="+wy+",resizable=yes,status=no,titlebar=yes");
            subwin.focus();
        }
        document.forms[0].yubinFlg.value = "0";
        //スクロール位置を保存された値に戻す
        document.body.scrollTop = document.forms[0].scrollTop.value;
    } else if(flg =="2") {
        window.alert("該当する住所が見つかりませんでした。");
        document.forms[0].yubinFlg.value = "0";
    } else if(flg == "E1") {
        window.alert("郵便番号の前３桁が入力されていません。");
        document.forms[0].yubinFlg.value="0";
    } else if(flg == "E2") {
        window.alert("郵便番号の後４桁が入力されていません。");
        document.forms[0].yubinFlg.value="0";
    } else if(flg == "E3") {
        window.alert("郵便番号の前３桁は数字３桁で入力してください。");
        document.forms[0].yubinFlg.value="0";
    } else if(flg == "E4") {
        window.alert("郵便番号の後４桁は数字４桁で入力してください。");
        document.forms[0].yubinFlg.value="0";
    } else if(flg == "E5") {
        window.alert("郵便番号の前３桁は半角数字で入力してください。");
        document.forms[0].yubinFlg.value="0";
    } else if(flg == "E6") {
        window.alert("郵便番号の後４桁は半角数字で入力してください。");
        document.forms[0].yubinFlg.value="0";
    }else if(flg == "0"){
        //スクロール位置を保存された値に戻す
        document.body.scrollTop = document.forms[0].scrollTop.value;
    }
}


//スクロール位置を保存する
function saveScrollTop(){
    document.forms[0].scrollTop.value = document.body.scrollTop;
}
//スクロール位置をセットする
function setScrollTop(){
    document.body.scrollTop = document.forms[0].scrollTop.value;
}

function inpClearConfirm(){
    return window.confirm('入力内容をクリアします。よろしいですか？');
}

function backMenuConfirmFirst(){
    if(window.confirm('入力内容をクリアしてメインメニューに戻ります。よろしいですか？')){
        location.href='../MenuAct.do';
    }
}
function backMenuConfirm(){
    if(window.confirm('ここまでの入力内容をクリアしてメインメニューに戻ります。よろしいですか？')){
        location.href='../MenuAct.do';
    }
}

function backMenuConfirmFirstDantai(url){
    if(window.confirm('入力内容をクリアしてメインメニューに戻ります。よろしいですか？')){
        location.href=url;
    }
}

function backMenuConfirmDantai(url){
    if(window.confirm('ここまでの入力内容をクリアしてメインメニューに戻ります。よろしいですか？')){
        location.href=url;
    }
}

function cancelConfirmHiddenValue(name){
    if(window.confirm('申込取消を行います。また、画面の変更内容は保存されません。\nよろしいですか？')){
        setHiddenValue(name);
        return true;
    }else{
        return false;
    }
        
}


function backMenuDantai(url){
    location.href=url;
}


function disabledOff(){
    document.forms[0].yubinNo1.disabled = false;
    document.forms[0].yubinNo2.disabled = false;
    document.forms[0].jusho1.disabled = false;
    document.forms[0].jusho2.disabled = false;
    document.forms[0].jusho3.disabled = false;
    document.forms[0].kaigaiJusho1.disabled = false;
    document.forms[0].kaigaiJusho2.disabled = false;
    document.forms[0].kaigaiJusho3.disabled = false;
    document.forms[0].gakkoName.disabled = false;
}

//ファイルが選択されていなかったら表示する。
function putMessage(){
    if (document.forms[0].photoFile.value=="") {
        var elm = document.getElementById('message01');
        elm.innerHTML = '選択されていません';
    }
    else{
        var elm = document.getElementById('message01');
        elm.innerHTML = '';
    }
}
