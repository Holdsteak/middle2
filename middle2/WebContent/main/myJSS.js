
let PK_exist;

document.addEventListener("DOMContentLoaded", function() {
	document.getElementById("button").disabled=true;
	document.getElementById("button").addEventListener("click", output);
	document.getElementById("move").addEventListener("change", change_move);
	document.getElementById("seq").addEventListener("blur", chkSeq);
});

	function ff(){
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				PK_exist=this.responseText;
				if(PK_exist==1 && document.getElementById("move").value=="新增"){
					document.getElementById("seq_res").innerHTML =  "<img src=Images/error.png> 該編號已存在"
						document.getElementById("button").disabled=true;
				}
				if(PK_exist==0 && document.getElementById("move").value!="新增"){
					document.getElementById("seq_res").innerHTML =  "<img src=Images/error.png> 該編號不存在"
						document.getElementById("button").disabled=true;
				}
			}
		}
		var seq=document.getElementById("seq").value;
		var url="http://localhost:8080/middle2/main/servlet?seq="+seq;
		xhttp.open("GET", url, true);
		xhttp.send();
	}

	 function output() {
		 let count=0;
		 let msg = document.querySelectorAll("div.msg");
		 for(let div of msg){
			 if(div.innerHTML!=""||document.getElementById("seq").value==""){
				document.getElementById("button").disabled=true;
				return;
			 }else{
				 count++;
				 if(count==8&&document.getElementById("seq").value!=""){
					 document.getElementById("button").disabled=false;
				 }
			 }
		}
		 
		if(document.getElementById("move").value=="新增"){
			let txt = document.querySelectorAll("input.txts");
			for(let input of txt){
				if(input.value==""){
					document.getElementById("button").disabled=true;
					return;
				}
			}
		} 
	 }
			 
	function change_move(){
		let msg = document.querySelectorAll("div.msg");
		for(let div of msg){
			div.innerHTML="";
		}
		let txt = document.querySelectorAll("input.txts");
		for(let input of txt){
			input.value="";
		}
		
		if(document.getElementById("move").value=="查詢" ||document.getElementById("move").value=="刪除"){
			document.getElementById("seq").addEventListener("blur", chkSeq);
			document.getElementById("year").removeEventListener("blur", chkYear);
			document.getElementById("dateListed").removeEventListener("blur", chkDates);
			document.getElementById("category").removeEventListener("blur", chkCG);
			document.getElementById("duration").removeEventListener("blur", chkDT);
			document.getElementById("schoolPopulation").removeEventListener("blur", function (){chkSP(this.name)});
			document.getElementById("transferPopulation").removeEventListener("blur", function (){chkSP(this.name)});
			document.getElementById("transferRates").removeEventListener("blur", function (){chkSP(this.name)});	
			output();
		}
		if(document.getElementById("move").value=="新增"||document.getElementById("move").value=="修改"){
			document.getElementById("seq").addEventListener("blur", chkSeq);
			document.getElementById("year").addEventListener("blur", chkYear);
			document.getElementById("dateListed").addEventListener("blur",chkDates);
			document.getElementById("category").addEventListener("blur", chkCG);
			document.getElementById("duration").addEventListener("blur", chkDT);
			document.getElementById("schoolPopulation").addEventListener("blur", function (){chkSP(this.name)});
			document.getElementById("transferPopulation").addEventListener("blur", function (){chkSP(this.name)});
			document.getElementById("transferRates").addEventListener("blur", function (){chkSP(this.name)});	
			output();
		}
	}
	
	function chkSeq() {
		let Integer_val = document.getElementById("seq").value.trim();
		let re = /^[0-9]{1,}$/;
		if(Integer_val==""){
			document.getElementById("seq_res").innerHTML =  "<img src=Images/error.png> 請輸入編號";
		}else if(re.test(Integer_val)&&Integer_val>0){
			for(let i=0;i<Integer_val.length;i++){
				let res=Integer_val.charAt(i);
				if(res=="."){
					document.getElementById("seq_res").innerHTML =  "<img src=Images/error.png> 請輸入大於0的整數"
					return;
				}else{
					document.getElementById("seq_res").innerHTML =  "";
				}
			}
			ff();
		}else{
			document.getElementById("seq_res").innerHTML =  "<img src=Images/error.png> 請輸入大於0的整數";
		}
		output();
	}
	
	function chkYear() {
		let Integer_val = document.getElementById("year").value.trim();
		let re = /^[0-9]{3}$/;
		if(Integer_val=="" && document.getElementById("move").value!="修改"){
			document.getElementById("year_res").innerHTML =  "<img src=Images/error.png> 請輸入年度";
		}else if(re.test(Integer_val)||(Integer_val==""&&document.getElementById("move").value=="修改")){
			document.getElementById("year_res").innerHTML = "";
		}else{
			document.getElementById("year_res").innerHTML =  "<img src=Images/error.png> 請輸入三位數字";
		}
		output();
	}
	
	function chkCG() {
		let String_val = document.getElementById("category").value;
		
		if(String_val=="" && document.getElementById("move").value!="修改"){
			document.getElementById("category_res").innerHTML =  "<img src=Images/error.png> 請輸入類別";
		}else{
			document.getElementById("category_res").innerHTML =  "";
		}
		output();
	}
	
	function chkDT() {
		let String_val = document.getElementById("duration").value;
		let re = /^[\u4e00-\u9fff]{2}$/;
		if(String_val=="" && document.getElementById("move").value!="修改"){
			document.getElementById("duration_res").innerHTML =  "<img src=Images/error.png> 請輸入學制";
		}else if(re.test(String_val)||(String_val==""&&document.getElementById("move").value=="修改")){
			document.getElementById("duration_res").innerHTML =  "";
		}else if(!re.test(String_val)&&String_val!=""){
			document.getElementById("duration_res").innerHTML =  "<img src=Images/error.png> 請輸入長度為二的中文字";
		}
		output();
	}
	
	function chkSP(value) {		
		let float_val = document.getElementById(value).value;
		let re = /^[0-9]{1,}$/;
		if(float_val==""&& value=="schoolPopulation" && document.getElementById("move").value!="修改"){
				document.getElementById(value+"_res").innerHTML =  "<img src=Images/error.png> 請輸入就學人數";				
		}else if(float_val==""&& value=="transferPopulation" && document.getElementById("move").value!="修改"){
				document.getElementById(value+"_res").innerHTML =  "<img src=Images/error.png> 請輸入轉出人數";				
		}else if(float_val==""&& value=="transferRates" && document.getElementById("move").value!="修改"){
				document.getElementById(value+"_res").innerHTML =  "<img src=Images/error.png> 請輸入轉學比例";				
		}else if(re.test(float_val)||(float_val==""&&document.getElementById("move").value=="修改")){
			document.getElementById(value+"_res").innerHTML = "";
		}else if(!re.test(float_val)&&float_val!=""){
			document.getElementById(value+"_res").innerHTML =  "<img src=Images/error.png> 請輸入數字";
		}
		output();
	}

	function chkDates() {
	    let dates_val = document.getElementById("dateListed").value;
		
	    let re = /^[0-9]{4}\-[0-9]{2}\-[0-9]{2}$/;
	    if (dates_val == ""&& document.getElementById("move").value!="修改") {
	        document.getElementById("dateListed_res").innerHTML = "<img src=Images/error.png> 請輸入日期";
	    } else if (re.test(dates_val)) {
	        let date_origin = dates_val.split("-");
	
	        let time = new Date(dates_val);
	        if (time) {
	            let time_year = time.getFullYear();
	            let time_month = time.getMonth() + 1;
	            let time_date = time.getDate();
	            
	            if (parseInt(date_origin[1]) != time_month) {
	                document.getElementById("dateListed_res").innerHTML = "<img src=Images/error.png> 日期無效";
	            }else {
	                document.getElementById("dateListed_res").innerHTML = "";
	            }
	        }
	    } else if(!re.test(dates_val)&&dates_val!="") {
	        document.getElementById("dateListed_res").innerHTML = "<img src=Images/error.png> 格式錯誤";
	    } else if(!re.test(dates_val)&&dates_val==""&& document.getElementById("move").value=="修改") {
	        document.getElementById("dateListed_res").innerHTML = "";
	    }
		output();
	}