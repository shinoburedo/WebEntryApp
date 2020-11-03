function saveScrollTop(){
    document.forms[0].scrollTop.value = document.body.scrollTop;
}

//郵便変換ポップアップをオープン！
var subwin;
function openYubin(){
    var wx = 600;
    var wy = 250;
    var x = (screen.width - wx) / 2;
    var y = (screen.height - wy) / 2;

    var url = './MypYubinSel.do';
    if(!subwin || subwin.closed){
        subwin = window.open(url, 'MypYubinSel',"menubar=no,scrollbars=yes,alwaysRaised=yes,top="+y+",left="+x+",width="+wx+",height="+wy+",resizable=yes,status=no,titlebar=yes");
        subwin.focus();
    }
    else{
        subwin = window.open(url, 'MypYubinSel',"menubar=no,scrollbars=yes,alwaysRaised=yes,top="+y+",left="+x+",width="+wx+",height="+wy+",resizable=yes,status=no,titlebar=yes");
        subwin.focus();
    }
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


//学校検索ポップアップをオープン！
function openGakko(){
    var wx = 600;
    var wy = 250;
    var x = (screen.width - wx) / 2;
    var y = (screen.height - wy) / 2;

    var url = './MypGakkoSel.do';
    if(!subwin || subwin.closed){
        subwin = window.open(url, 'MypGakkoSel',"menubar=no,scrollbars=yes,alwaysRaised=yes,top="+y+",left="+x+",width="+wx+",height="+wy+",resizable=yes,status=no,titlebar=yes");
        subwin.focus();
    }
    else{
        subwin = window.open(url, 'MypGakkoSel',"menubar=no,scrollbars=yes,alwaysRaised=yes,top="+y+",left="+x+",width="+wx+",height="+wy+",resizable=yes,status=no,titlebar=yes");
        subwin.focus();
    }
}

var count=0;
function objectToBlank(name){
    if(count == 0){
        document.forms[0].elements[name].value ="";
        count++;
    }
}


var dsBgColor = "#d4d4d4";
function dantaiInpDisabled(){
    if(document.forms[0].kojinDantaiKbn[1].checked){
        document.forms[0].moshikomiDantaiCode.disabled = "";
        document.forms[0].searchDantai.disabled = "";
        document.forms[0].moshikomiDantaiCode.style.backgroundColor="";
    }else{
        document.forms[0].moshikomiDantaiCode.value = "";
        document.forms[0].moshikomiDantaiCode.disabled = "true";
        document.forms[0].searchDantai.disabled = "true";
        document.forms[0].moshikomiDantaiCode.style.backgroundColor=dsBgColor;
        document.getElementById('dantaiNameDisp').style.display = "none";
    }
}

function junKaijoInpDisabled(){
    if(document.forms[0].isMenjoAll.value=="false"){
        if(document.forms[0].shikenchiCode1.value=="000"){
            document.forms[0].junKaijoCode.disabled = "";
            document.forms[0].searchJunKaijo.disabled = "";
            document.forms[0].junKaijoCode.style.backgroundColor="";
        }else{
            document.forms[0].junKaijoCode.value = "";
            document.forms[0].junKaijoCode.disabled = "true";
            document.forms[0].searchJunKaijo.disabled = "true";
            document.forms[0].junKaijoCode.style.backgroundColor=dsBgColor;
        }
    }
}
          
function jushoInpDisabled(){
    if(document.forms[0].kaigaiJushoFlg[1].checked){
        document.forms[0].jushoKaigai1.disabled = "";
        document.forms[0].jushoKaigai2.disabled = "";
        document.forms[0].jushoKaigai3.disabled = "";
        document.forms[0].jusho1.disabled = "true";
        document.forms[0].jusho2.disabled = "true";
        document.forms[0].jusho3.disabled = "true";
        document.forms[0].yubinNo1.disabled = "true";
        document.forms[0].yubinNo2.disabled = "true";
        document.forms[0].searchYubin.disabled = "true";
        document.forms[0].jusho1.style.backgroundColor=dsBgColor;
        document.forms[0].jusho2.style.backgroundColor=dsBgColor;
        document.forms[0].jusho3.style.backgroundColor=dsBgColor;
        document.forms[0].yubinNo1.style.backgroundColor=dsBgColor;
        document.forms[0].yubinNo2.style.backgroundColor=dsBgColor;
        document.forms[0].jushoKaigai1.style.backgroundColor="";
        document.forms[0].jushoKaigai2.style.backgroundColor="";
        document.forms[0].jushoKaigai3.style.backgroundColor="";
        document.getElementById('reqKokunaiDisp1').style.display = 'none';
        document.getElementById('reqKokunaiDisp2').style.display = 'none';
        document.getElementById('reqKaigaiDisp').style.display = '';
    }else{
        document.forms[0].jusho1.disabled = "";
        document.forms[0].jusho2.disabled = "";
        document.forms[0].jusho3.disabled = "";
        document.forms[0].yubinNo1.disabled = "";
        document.forms[0].yubinNo2.disabled = "";
        document.forms[0].searchYubin.disabled = "";
        document.forms[0].jushoKaigai1.disabled = "true";
        document.forms[0].jushoKaigai2.disabled = "true";
        document.forms[0].jushoKaigai3.disabled = "true";
        document.forms[0].jushoKaigai1.style.backgroundColor=dsBgColor;
        document.forms[0].jushoKaigai2.style.backgroundColor=dsBgColor;
        document.forms[0].jushoKaigai3.style.backgroundColor=dsBgColor;
        document.forms[0].jusho1.style.backgroundColor="";
        document.forms[0].jusho2.style.backgroundColor="";
        document.forms[0].jusho3.style.backgroundColor="";
        document.forms[0].yubinNo1.style.backgroundColor="";
        document.forms[0].yubinNo2.style.backgroundColor="";
        document.getElementById('reqKokunaiDisp1').style.display = '';
        document.getElementById('reqKokunaiDisp2').style.display = '';
        document.getElementById('reqKaigaiDisp').style.display = 'none';
    }
                   
}
            
function gakkoInpDisabled(){
    if(document.forms[0].shokugyoCode.value=="1"){
        document.forms[0].gakkoName.disabled = "";
        document.forms[0].gakkoKbn.disabled = "";
        document.forms[0].gakkoCode.disabled = "";
        document.forms[0].gakunen.disabled = "";
        document.forms[0].searchGakko.disabled = "";
        document.forms[0].gakkoName.style.backgroundColor="";
        document.forms[0].gakkoKbn.style.backgroundColor="";
        document.forms[0].gakkoCode.style.backgroundColor="";
        document.forms[0].gakunen.style.backgroundColor="";
        document.forms[0].kinmusakiName.disabled = "true";
        document.forms[0].kinmusakiName.style.backgroundColor=dsBgColor;
  
    }else if(document.forms[0].shokugyoCode.value==""){
        document.forms[0].gakkoName.disabled = "true";
        document.forms[0].gakkoKbn.disabled = "true";
        document.forms[0].gakkoCode.disabled = "true";
        document.forms[0].gakunen.disabled = "true";     
        document.forms[0].searchGakko.disabled = "true"; 
        document.forms[0].gakkoName.style.backgroundColor=dsBgColor;
        document.forms[0].gakkoKbn.style.backgroundColor=dsBgColor;
        document.forms[0].gakkoCode.style.backgroundColor=dsBgColor;
        document.forms[0].gakunen.style.backgroundColor=dsBgColor;
        document.forms[0].kinmusakiName.disabled = "true";
        document.forms[0].kinmusakiName.style.backgroundColor=dsBgColor;
  
    }else{
        document.forms[0].gakkoName.disabled = "true";
        document.forms[0].gakkoKbn.disabled = "true";
        document.forms[0].gakkoCode.disabled = "true";
        document.forms[0].gakunen.disabled = "true";     
        document.forms[0].searchGakko.disabled = "true"; 
        document.forms[0].gakkoName.style.backgroundColor=dsBgColor;
        document.forms[0].gakkoKbn.style.backgroundColor=dsBgColor;
        document.forms[0].gakkoCode.style.backgroundColor=dsBgColor;
        document.forms[0].gakunen.style.backgroundColor=dsBgColor;
        document.forms[0].kinmusakiName.style.backgroundColor="";
        document.forms[0].kinmusakiName.disabled = "";
    }
}
            
function yubinNoKensaku(){
                
    if(document.forms[0].yubinKensakuFlg.value=="1"){
        document.body.scrollTop = document.forms[0].scrollTop.value;
        if (document.forms[0].yubinErrKbn.value == "1") {
            window.alert("郵便番号が間違っています。入力を確認してください。");
        } else if (document.forms[0].yubinErrKbn.value == "2") {
            window.alert("郵便番号が入力されていません。");
        }else if (document.forms[0].yubinErrKbn.value == "3") {
            window.alert("郵便番号は半角数字７桁で入力してください。");
        }else if(document.forms[0].yubinErrKbn.value=="0"){
                        
            if(document.forms[0].yubinOpenFlg.value=="1"){
                openYubin();
            } 
        }
    }
}
            
function dantaiKensaku(){
    if(document.forms[0].dantaiKensakuFlg.value=="1"){
        document.body.scrollTop = document.forms[0].scrollTop.value; 
                    
        if (document.forms[0].dantaiErrKbn.value == "1") {
            window.alert("該当する団体が見つかりませんでした。");
        } else if (document.forms[0].dantaiErrKbn.value == "2") {
            window.alert("団体コードが入力されていません。");
        } else if (document.forms[0].dantaiErrKbn.value == "3") {
            window.alert("団体コードは半角数字５桁で入力してください。");
        }else if(document.forms[0].dantaiErrKbn.value=="0"){
                        
    }
    }
}
            
function junkaijoKensaku(){
    if(document.forms[0].junkaijoKensakuFlg.value=="1"){
        document.body.scrollTop = document.forms[0].scrollTop.value; 
                    
        if (document.forms[0].junkaijoErrKbn.value == "1") {
            window.alert("該当する準会場が見つかりませんでした。");
        } else if (document.forms[0].junkaijoErrKbn.value == "2") {
            window.alert("準会場コードが入力されていません。");
        } else if (document.forms[0].junkaijoErrKbn.value == "3") {
            window.alert("準会場コードは半角数字６桁で入力してください。");
        } else if(document.forms[0].junkaijoErrKbn.value=="0"){
            document.body.scrollTop = document.forms[0].scrollTop.value; 
        }
    }
}
            
function gakkoKensaku(){
                
    if(document.forms[0].gakkoKensakuFlg.value=="1"){
        document.body.scrollTop = document.forms[0].scrollTop.value;
        if (document.forms[0].gakkoErrKbn.value == "1") {
            window.alert("該当する学校が見つかりませんでした。");
        } else if (document.forms[0].gakkoErrKbn.value == "2") {
            window.alert("学校名が入力されていません。");
        }else if (document.forms[0].gakkoErrKbn.value == "3") {
            window.alert("学校名は全角３０桁以内で入力してください。");
        }else if(document.forms[0].gakkoErrKbn.value=="0"){
                        
            if(document.forms[0].gakkoOpenFlg.value=="1"){
                openGakko();
            } 
        }
    }
}
            