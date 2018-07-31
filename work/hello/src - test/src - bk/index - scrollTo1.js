import React from 'react'
import './App.css'
import  scrollToElement from 'scroll-to-element';

import messi1 from './images/messi1.jpg';
import messi2 from './images/messi2.jpg';
import messi3 from './images/messi3.jpg';
import messi4 from './images/messi4.jpg';


class App extends React.Component {	
constructor(props){
	super(props);	
	this.fnTest.bind(this);
	this.showMore.bind(this);
	this.showLess.bind(this);
	this.switchButtons.bind(this);
	document.addEventListener("keydown", this.fnKeyHandler.bind(this));
	
}
	

componentDidMount() {
        console.log("Did mount");
		this.showMore();
    }
	
fnTest(){
		var listDatas = document.querySelectorAll('#dataList li:not(.shown)');
		console.log(listDatas);
	}
 showMore() {
		var listData = Array.prototype.slice.call(document.querySelectorAll('#dataList li:not(.shown)')).slice(0, 3);
		//console.log(listData);
		for (var i=0; i < listData.length; i++) {
			listData[i].className  = 'shown';
		}
		this.switchButtons();
		this.fnTest();
	}

showLess() {
	  console.log("adsd");
	  var listData = Array.prototype.slice.call(document.querySelectorAll('#dataList li:not(.hidden)')).slice(-3);
	  for (var i=3; i < listData.length; i++){
		listData[i].className  = 'hidden';
	  }
	  this.switchButtons();
	}

 switchButtons() {
	  var hiddenElements = Array.prototype.slice.call(document.querySelectorAll('#dataList li:not(.shown)'));
	  if(hiddenElements.length == 0){
		document.getElementById('moreButton').style.display = 'none';
		
	  }
	  else{
		document.getElementById('moreButton').style.display = 'block';
	  }

	  var shownElements = Array.prototype.slice.call(document.querySelectorAll('#dataList li:not(.hidden)'));
	  if(shownElements.length == 0){
		document.getElementById('lessButton').style.display = 'none';
	  }
	  else{
		document.getElementById('lessButton').style.display = 'block';
	  }
	}

	onload= function(){
		this.showMore();
	}


 fnKeyHandler(e){
		//debugger;
		//console.log(e);
		if(e.key == "ArrowDown"){
			//console.log("ArrowDown");
			this.showMore();
			var elem=document.querySelector('.moreButton');
				scrollToElement(elem, {
				offset: 0,
				ease: 'out-bounce',
				duration: 1000
			});
			
		}else if(e.key == "ArrowUp"){
			//console.log("ArrowUp");
			this.showLess();
			var elem=document.querySelector('.lessButton');
			scrollToElement(elem, {
				offset: 0,
				ease: 'out-bounce',
				duration: 1000
			});
			
		}
	}

  render () {
    return (
      <div className="scrollableArea">
	<div className="lessButton" id="lessButton" onclick="showLess()"> </div>
	<ul id="dataList">
	    <li className="hidden"><a>Lorem Ipsum is </a></li>
	    <li className="hidden">
	    	<div>
				 <img src={messi1}/>
				 <img src={messi2}/>
				 <img src={messi3}/>
				 <img src={messi4}/>
	    	</div>
	    </li>
	    <li className="hidden">
	    	<div>
	    		<img src={messi1}/>
				 <img src={messi2}/>
				 <img src={messi3}/>
				 <img src={messi4}/>
	    	</div>
	    </li>
	    <li className="hidden"><a>Simply dummy</a></li>
	    <li className="hidden"><a>long established</a></li>
	    <li className="hidden">
	    	<div>
	    		<img src={messi1}/>
				 <img src={messi2}/>
				 <img src={messi3}/>
				 <img src={messi4}/>
	    	</div>
	    </li>
	    <li className="hidden">
	    	<div>
	    		<img src={messi1}/>
				 <img src={messi2}/>
				 <img src={messi3}/>
				 <img src={messi4}/>
	    	</div>
	    </li>
	    <li className="hidden">
	    	<div>
	    		<img src={messi1}/>
				 <img src={messi2}/>
				 <img src={messi3}/>
				 <img src={messi4}/>
	    	</div>
	    </li>
	    <li className="hidden"><a>Variations of passages</a></li>
	    <li className="hidden"><a>Characteristic words</a></li>
	    <li className="hidden"><a>Finibus Bonorum </a></li>
	    <li className="hidden"><a>Thirteen</a></li>
	    <li className="hidden"><a>Embarrassing</a></li>
	    <li className="hidden"><a>Characteristic words</a></li>
	    <li className="hidden"><a>Finibus Bonorum </a></li>
	    <li className="hidden"><a>Thirteen</a></li>
	    <li className="hidden"><a>Embarrassing</a></li>
	    <li className="hidden">
	    	<div>
	    		<img src={messi1}/>
				 <img src={messi2}/>
				 <img src={messi3}/>
				 <img src={messi4}/>
	    	</div>
	    </li>
	    <li className="hidden">
	    	<div>
	    		<img src={messi1}/>
				 <img src={messi2}/>
				 <img src={messi3}/>
				 <img src={messi4}/>
	    	</div>
	    </li>
	</ul>
	<div className="moreButton" id="moreButton" onclick="showMore()"> </div>
	</div>
    )
  }
}

export default App
