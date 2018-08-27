import React, { Component } from 'react';
import './App.css';

var db, request, objectStore, cursor; 
class App extends Component {	
	constructor(props){
		super(props);
		this.state = {			
			data:{}
		}
		this.InitDb = this.InitDb.bind(this);
		this.add = this.add.bind(this);
		this.readAll = this.readAll.bind(this);
	}
	
	// DB initialize
	InitDb(){
		var indexedDB = window.indexedDB || window.mozIndexedDB || window.webkitIndexedDB || window.msIndexedDB; 
         if (indexedDB) {
            console.log("Your browser doesn't support a stable version of IndexedDB.");
         }else{
			console.log("Suported");
		 }		 
		 const chatbotData = [
            { time: "2017.10.10", position: "left", msgtype: "text",msg:"My Name is Sentu Biswas" },
            { time: "2017.10.11", position: "right", msgtype: "text",msg:"How can I help you?" }
         ];
		
         request = window.indexedDB.open("chatbotDB", 1);
         request.onerror = function(event) {
            console.log("error: ");
         };
		 
         request.onsuccess = function(event) {
             db = request.result;
            console.log("success: "+ db);
         };
         
         // Create table and insert data to the chatbot table
         request.onupgradeneeded = function(event) {
            db = event.target.result;
            objectStore = db.createObjectStore("chatbot", {keyPath: "id", autoIncrement:true});
            
            for (var i in chatbotData) {
               objectStore.add(chatbotData[i]);
            }
         } 
	}
	componentWillMount(){
		this.InitDb();
	}
	add(){
		request = db.transaction(["chatbot"], "readwrite").objectStore("chatbot")
		.add({ time: "2017.10.17", position: "left", msgtype: "text",msg:"Thank you" })            
		request.onsuccess = function(event) {
		   alert("Data added Successfully");
		}		
		request.onerror = function(event) {
		   alert("Already exist in your database! ");
		}
	}
	
	readAll() {
		var th = this;
		var dataitems = [];
		var objectStore = db.transaction("chatbot").objectStore("chatbot");
		objectStore.openCursor().onsuccess = function(event) {
		   cursor = event.target.result;
		   if (cursor) {
			 //console.log(cursor);	
			 dataitems.push(cursor.value);
			
			  cursor.continue();
		   }		   
		   else {
			  console.log("No more entries!");
		   }
		   th.setState({
			 data:dataitems
		 })
		};
		 
	 }
	
  render() {
	console.log("dataaa");
	console.log(this.state.data);
	//let { dataList} = this.state.data.value;

    return (
      <div className="App">
		  <button onClick={this.add}>Add data </button>
		  <button onClick={this.readAll}>Read all </button>
		  <div className="chatBox">
		  {
			  Object.keys(this.state.data).map((item, index) => {
				  return(
						<p key={index}><span className={this.state.data[item].position == "left" ? "left" : "right"}>{ this.state.data[item].msg } </span></p>
				  )
			  })
		  }
		  </div>		  
      </div>
    );
  }
}


export default App;
