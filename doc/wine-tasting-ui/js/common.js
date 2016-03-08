function popup(text,time){
	//增加新的div
	var oDiv=document.getElementById("nBox");
	if(null == oDiv || undefined == oDiv){
		oDiv = document.createElement("div");
		oDiv.style.border="1px solid black";
		oDiv.style.width="100px";
		oDiv.style.height="28px";
		oDiv.style.backgroundColor="rgba(0,0,0,.8)";
		oDiv.style.zIndex='5';
		oDiv.style.position='absolute';
		oDiv.style.left='50%';
		oDiv.style.top='50%';
		oDiv.style.marginLeft='-50px';
		oDiv.style.marginTop='-14px';
		oDiv.style.color='#fff';
		oDiv.innerHTML=text;
		oDiv.style.lineHeight='28px';
		oDiv.style.textAlign='center';
		oDiv.id='nBox'; 
		document.body.appendChild(oDiv);
	}
	else{
		oDiv.style.display='block'; 
	}	
}

function disappear(){
	var newDiv=document.getElementById('nBox');
	newDiv.style.display='none'; 
}
