document.addEventListener("keydown", this.keydownHandler);
var count = 0;

function keydownHandler(e){
	//console.log(e);
	var li = document.querySelectorAll(".screenList li");
	var liTotal = li.length;
	console.log(liTotal);
	if(e.key == "ArrowRight"){
		if(count == 0){
			li[count].className = "focus";
			count ++;
		}else if(count > 0){
			li[count-1].className = "";
			li[count].className = "focus";
			count ++;
			if(count> liTotal-1){
				count = liTotal-1;
			}
		}
			
	}else if(e.key == "ArrowLeft"){
		console.log("ArrowUp");
		if(count > 0){
			li[count-1].className = "focus";
			li[count].className = "";
			count --;
			if(count < -1){
				count = 0;
			}
		}
	}	
}
