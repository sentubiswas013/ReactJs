import React, { Component } from 'react';
import './App.css';
import Recorder from './Recorder';

import icon1 from './assets/images/gifs/listening.gif'
import icon2 from './assets/images/gifs/processing.gif'
import icon3 from './assets/images/pngs/icon_voice_80x80.png'


let  value="",condition=false,pros=false;
class App extends Component {
  constructor(...args) {
    super(...args);

    this.state = {
      blob: null,
	  string:"",
	  image: icon1,
      isRecording: false,
      stream: null,
      analyserData: {data: [], lineTo: 0},
    };

    this.start = this.start.bind(this);
    this.stop = this.stop.bind(this);
    this.download = this.download.bind(this);

    this.audioContext = new (window.AudioContext || window.webkitAudioContext)();

    this.recorder = new Recorder(this.audioContext, {
      onAnalysed: data => this.setState({analyserData: data}),
    });

    navigator.mediaDevices.getUserMedia({audio: true})
      .then((stream) => {
        this.setState({stream});
        this.recorder.init(stream);
      })
      .catch(this.dontGotStream);
  }
  
  shouldComponentUpdate(){
	  
	  pros?	(condition? this.setState({ string:value,image:icon3}):this.setState({ string:"processing...",image:icon2})):
	  this.setState({string:"Listening..."})
	  
//condition? this.setState({ string:value,image:icon3}):
		
	  return true;
  }

  start() {
    this.recorder.start()
      .then(() => this.setState({
		  isRecording: true,
	  }));
  }

  stop() {
      this.recorder.stop()
      .then(({blob}) => this.setState({
        isRecording: false,
		 string:"Processing...",
        blob,
      }));
	  pros=true;
	  this.download;
  }

  dontGotStream(error) {
    console.log('Get stream failed', error);
  }

  download() {
     var reader = new window.FileReader();
     reader.readAsDataURL(this.state.blob); 
     reader.onloadend = function(e) {
        var http = new XMLHttpRequest({
            mozSystem: true
        });
       var base64data = e.target.result;
        var audio = base64data.split(',');
       
   
			var data = '{"config":{"encoding":"LINEAR16","sample_rate_hertz":"48000","languageCode":"' + "en-IN" + '"},"audio":{"content":"' + audio[1] + '"}}'; 
        

        var googleSpeechURL ="https://speech.googleapis.com/v1/speech:recognize?key=AIzaSyAt8FuLEsAfhfbsyc_5ZkR3jtVpYFVLPls";
        http.open("POST", googleSpeechURL, true);
        http.send(data);
     
        http.onreadystatechange = function () {
            if (http.readyState === 4 && http.status === 200) {
                var resultantText = '';
                var response = JSON.parse(http.responseText);
				debugger;
				if(response.results==undefined){
					 value="Sorry I didn't hear anything";
				}
				else{
					 value=response.results[0].alternatives[0].transcript;
				}
					
					 condition=true;
					 console.log(value);
            } 
        };
    }

	 // function() {
             // var    base64data = reader.result;                
                // console.log(base64data );
     // }  	
	this.setState({
		 blob: null,
		 string:"Processing"
		 });
    Recorder.download(this.state.blob, 'react-audio');
    
  }

  render() {
    const {
      isRecording,
      blob,
      stream,
    } = this.state;

    return (
      <div className="App">
        <div className="App-header">
		
		  <img src={this.state.image}/>
          <h2>{this.state.string}</h2>

          <div className="App-buttons">
            {isRecording ? (
              <button onClick={this.stop}>Stop</button>
            ) : (
              <button onClick={this.start}>Start</button>
            )}
            {blob && (
              <button
                onClick={this.download}
              >
                Download
              </button>
            )}
          </div>
        </div>
      </div>
    );
  }
}

export default App;
