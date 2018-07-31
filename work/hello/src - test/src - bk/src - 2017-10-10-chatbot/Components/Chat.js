import React from 'react';
import {withRouter} from 'react-router-dom';
import Header from '../Components/Header';
import Container from '../Components/Container';
import Footer from '../Components/Footer';
import ErrorScreen from '../Components/ErrorScreen';
import TextToSpeech from '../tts';
import ScrollTo from 'scroll-to-element';
import axios from 'axios';
const quickReplies = 'quick_replies';
const attachments = 'attachment';
var noOfButton = 0;
var nextIndex = -1, NUMBER_OF_ITEMS = 0, slideIndex = -1, noOfButtonsInCarousal = -1, startIndexOfCarousal = -1;
var pathArray = [];
var latestReqParam = "";


const Chat = React.createClass({
  // This is callback of react life cycle and its called only once. We initialize all the parameters
  // required to default values.
  getInitialState() {
       return {
         data: [],
         message: '',
        updated : false,
        image : false,
        isQuickReply: false,
        isCarousal: false,
        isTextView: false,
        isBusyNetwork: false,
        requestUrl: "https://ec2-35-156-49-41.eu-central-1.compute.amazonaws.com:8445/rcsbotsgateway/apps/callbot",
        address : '"addresses":{"from":"+351770000025","participants":["+351932475200","+351770000025"]}',
        prov : "",
        requestParameter : "",
        provider : "",
        request : '"request":{"message":{ "text": "resetme"}}'
       };
  },

  // This function makes a network call using the NICKNAME of the bot to provide providerDetails.
  getProviderDetails() {
    let th = this;
    axios.get('https://ec2-35-156-49-41.eu-central-1.compute.amazonaws.com:8445/rcsbotsgateway/apps/bots?nickname='+this.props.match.params.id.split(",")[0]+'&callbotsupport=1')
      .then(function(result) {
        let temp = '"provider":'+JSON.stringify(result.data);
        th.setState({
          provider : temp,
        });
        th.requestMumbailocalData();
      })
      .catch(function(err) {
      })
  },

  // This function is called to reset the member variables to default values.
  // When we use router, the values will be rtained and it might cause side effetcs.
  // Hence its our duty to reset when ever necessary.
  resetMemberVariables() {
    noOfButton = 0;
    nextIndex = -1, NUMBER_OF_ITEMS = 0, slideIndex = -1, noOfButtonsInCarousal = -1;
    pathArray = [];
    latestReqParam = "";
  },

  // This is a react lifecyle callback. Here, we are removing the event listener for key down.
  componentWillUnmount() {
    document.removeEventListener("keydown", this.handleKeyDown);
  },

  // This is a react lifecyle callback. Here, we are adding the event listener for key down
  // and setting the request parameter.
  componentWillMount() {
      this.getProviderDetails();
      this.resetMemberVariables();
      if (this.props.match.params.id.split(",")[0]=== "@railenquiry") {
        this.setState({
          request : '"request":{"message":{ "text": "resetme"}}',
        });
      } else {
        this.setState({
          request : '"request":{"message":{ "text": "help"}}',
        });
      }
      document.addEventListener("keydown", this.handleKeyDown);
  	},

  componentDidMount() {

  },

  componentDidUpdate() {

  },

  // This is React lifecylce call. We are updating the component only if "updated" state
  // variable is modified. This is a performance optimization.
  shouldComponentUpdate(nextProps, nextState) {

    if (this.state.updated !== nextState.updated) {
      return true;
    }
    return false;
  },

  // This function is called whenever the slide in the carousal changes.
  // Here, we handle the logic of selecting the first button of any carousal slide.
  updateNoOfButtoninCarousal(slideInx, tltBtns, indexToBeSelected) {
      let domElems = this.getDOMElements();
      let buttonElement = domElems.Button;
      if (buttonElement === undefined) {
        return;
      }
      if (slideInx === -1 || tltBtns === -1 || tltBtns === 0) {
        return;
      } else {
        slideIndex = slideInx + 1;
        noOfButtonsInCarousal = tltBtns;
        startIndexOfCarousal = indexToBeSelected;
        if (nextIndex === -1) {
          nextIndex = 0;
          var elem = document.querySelector("button");
          if (elem === null || elem === undefined) {
            return this;
          }
    			elem.className += " selected";
          ScrollTo ('.selected',{offset:0,align: 'middle',duration: 1500});
          return;
        }
        let clsNameUnselected = domElems.Button[nextIndex].className;
        clsNameUnselected = clsNameUnselected.replace(' selected', '');
        domElems.Button[nextIndex].className = clsNameUnselected;
        domElems.Button[nextIndex].className += " unselected"
        nextIndex = indexToBeSelected;
        let clsNameSelected = domElems.Button[indexToBeSelected].className;
        clsNameSelected = clsNameSelected.replace(' unselected', '');
        let elem = domElems.Button[indexToBeSelected];
        domElems.Button[indexToBeSelected].className = clsNameSelected
        elem.className += " selected";
        ScrollTo ('.selected',{offset:0,align: 'middle',duration: 1500});
      }
  },

  // This function handles the functionality of "Right Soft Key" button press
  handleSoftRightKey() {
    document.getElementById("footerMsg").blur();
      var txt = document.getElementById('footerMsg').value;
      if(txt.length>1){
        var req = '"request":{"message":{ "text": "'+txt+'"}}';
        document.getElementById('footerMsg').value="";
         this.requestMumbailocalData(undefined, undefined, req);
      }
  },

  // This is function parses the JSON response to give us the OBJECT with value "messages"
  findTemplatesInResponse(obj, path, template) {
    for (var property in obj) {
      if (obj.hasOwnProperty(template) && !path.includes("request")) {
        var regexForNumber = /\d+/;
        let number = path.match(regexForNumber);
        var r = /[.]\d+/;
        let temp =  path.match(r);
        path = path.replace(temp, "["+number+"]");
        pathArray.push(obj[template]);
      }
      if (obj.hasOwnProperty(property)) {
        if (typeof obj[property] === "object") {
          this.findTemplatesInResponse(obj[property], path + '.' + property, template);
        }
      }
    }
  },

  // This function is used to make the POST calls to the BOT and the response is handled
  // and assigned to the state variables accordingly.
  requestMumbailocalData(title, payload, reqq) {
      if (this.state.isBusyNetwork) {
        return;
      }
      let th = this;
      var jsonStr = "";
      if (reqq === undefined) {
        jsonStr = '{'+ this.state.address + ',' + this.state.provider + ',' + this.state.request + '}';
        latestReqParam = this.state.request;
      } else {
        jsonStr = '{'+ this.state.address + ',' + this.state.provider + ',' + reqq + '}';
        latestReqParam = reqq;
      }
      slideIndex = -1, noOfButtonsInCarousal = -1;
      var cObj = JSON.parse(jsonStr);
      nextIndex = -1;
      th.setState({
        updated : false,
        isBusyNetwork: true,
      });

      axios.post(this.state.requestUrl, cObj).then(function (response) {
          th.findTemplatesInResponse(response, '', "message");
          th.setState({
            message : ""
          });
          for (var prop in pathArray) {
            if (pathArray[prop].hasOwnProperty("text")) {
              th.setState({
                message : pathArray[prop].text
              });
            }

            if (pathArray[prop].hasOwnProperty(quickReplies)) {
              th.setState({
                data: pathArray[prop],
                image : false,
                prov : response.request["response"],
                isQuickReply: true,
                isCarousal: false,
                isTextView: false,
                isBusyNetwork: false,
               });
            }

            if (pathArray[prop].hasOwnProperty(attachments)) {
              th.setState({
                data: pathArray[prop].attachment.payload.elements,
                image : true,
                prov : response.request["response"],
                isQuickReply: false,
                isCarousal: true,
                isTextView: false,
                noOfCarousal: pathArray[prop].attachment.payload.elements.length,
                carousalPosition: 0,
                isBusyNetwork: false,
               });
            }

            if (!pathArray[prop].hasOwnProperty(attachments) && !pathArray[prop].hasOwnProperty(quickReplies)) {
              th.setState({
                data: pathArray[prop],
                image : false,
                isQuickReply: false,
                isCarousal: false,
                isTextView: true,
                isBusyNetwork: false,
               });
            }
          }

          th.setState({
            updated : true,
           });
           let config ={"language":"en-US","gender":"Female"};
           let text= th.state.message;
           if (text.length > 0) {
             TextToSpeech.indiantts(text);
           }
          pathArray.splice(0,pathArray.length);
      }).catch(function (error) {
            alert("error");
            th.setState({
              updated : true,
              isBusyNetwork: false,
             });
    });
    },

  // This function handles the KEYDOWN events of the carousal
  handleKeyDown(e) {
    if (this.context.router.history.location === "/") {
      return;
    }
    if (this.state.isBusyNetwork) {
      return;
    }

    var domElems = this.getDOMElements(),
        isUIInitialized = false;
    this.NUMBER_OF_ITEMS = domElems.Button.length - 1;
    if (slideIndex !== -1 || noOfButtonsInCarousal !== -1) {
      this.NUMBER_OF_ITEMS = noOfButtonsInCarousal - 1;
    }

    if (nextIndex === -1 && (e.key === "ArrowDown" || e.key === "ArrowUp")) {
			nextIndex = 0;
			var elem = document.querySelector("button");
      if (elem === null || elem === undefined) {
        return this;
      }
			elem.className += " selected";
      ScrollTo ('.selected',{offset:0,align: 'middle',duration: 1500});
			return this;
		} else if (e.key === "ArrowDown") {
        if (slideIndex !== -1 || noOfButtonsInCarousal !== -1) {
          if (nextIndex+1 === (startIndexOfCarousal + noOfButtonsInCarousal)) {
            let clsNameUnselected = domElems.Button[nextIndex].className;
            clsNameUnselected = clsNameUnselected.replace(' selected', '');
            domElems.Button[nextIndex].className = clsNameUnselected;
						domElems.Button[nextIndex].className += " unselected"
						nextIndex = startIndexOfCarousal;
            let elem = domElems.Button[nextIndex];
						if((nextIndex%noOfButtonsInCarousal) <= this.NUMBER_OF_ITEMS) {
              let clsNameSelected = domElems.Button[nextIndex].className;
              clsNameSelected = clsNameSelected.replace(' unselected', '');
              domElems.Button[nextIndex].className = clsNameSelected
            elem.className += " selected";
              ScrollTo ('.selected',{offset:0,align: 'middle',duration: 1500});
            }
          } else {
            let clsNameUnselected = domElems.Button[nextIndex].className;
            clsNameUnselected = clsNameUnselected.replace(' selected', '');
            domElems.Button[nextIndex].className = clsNameUnselected;
            domElems.Button[nextIndex].className += " unselected"
            nextIndex = nextIndex + 1;
            let elem = domElems.Button[nextIndex];
              let clsNameSelected = domElems.Button[nextIndex].className;
              clsNameSelected = clsNameSelected.replace(' unselected', '');
              domElems.Button[nextIndex].className = clsNameSelected
              elem.className += " selected";
              ScrollTo ('.selected',{offset:0,align: 'middle',duration: 1500});
          }
        } else if (nextIndex === this.NUMBER_OF_ITEMS) {
            let clsNameUnselected = domElems.Button[nextIndex].className;
            clsNameUnselected = clsNameUnselected.replace(' selected', '');
            domElems.Button[nextIndex].className = clsNameUnselected;
						domElems.Button[nextIndex].className += " unselected"
						nextIndex = 0;
						let elem = domElems.Button[nextIndex];
						if(nextIndex <= this.NUMBER_OF_ITEMS) {
              let clsNameSelected = domElems.Button[nextIndex].className;
              clsNameSelected = clsNameSelected.replace(' unselected', '');
              domElems.Button[nextIndex].className = clsNameSelected
            elem.className += " selected";
              ScrollTo ('.selected',{offset:0,align: 'middle',duration: 1500});
            }
					} else {
              let clsNameUnselected = domElems.Button[nextIndex].className;
              clsNameUnselected = clsNameUnselected.replace(' selected', '');
              domElems.Button[nextIndex].className = clsNameUnselected;
							domElems.Button[nextIndex].className += " unselected"
							nextIndex = nextIndex + 1;
							let elem = domElems.Button[nextIndex];
							if(nextIndex <= this.NUMBER_OF_ITEMS) {
                let clsNameSelected = domElems.Button[nextIndex].className;
                clsNameSelected = clsNameSelected.replace(' unselected', '');
                domElems.Button[nextIndex].className = clsNameSelected
                elem.className += " selected";
                ScrollTo ('.selected',{offset:0,align: 'middle',duration: 1500});
              }
					}
      } else if (e.key === "ArrowUp") {
        if (slideIndex !== -1 || noOfButtonsInCarousal !== -1) {
          if ((nextIndex ) === startIndexOfCarousal) {
            let clsNameUnselected = domElems.Button[nextIndex].className;
            clsNameUnselected = clsNameUnselected.replace(' selected', '');
            domElems.Button[nextIndex].className = clsNameUnselected;
						domElems.Button[nextIndex].className += " unselected"
						nextIndex = startIndexOfCarousal + noOfButtonsInCarousal - 1;
            let elem = domElems.Button[nextIndex];
						if((nextIndex%noOfButtonsInCarousal) <= this.NUMBER_OF_ITEMS) {
              let clsNameSelected = domElems.Button[nextIndex].className;
              clsNameSelected = clsNameSelected.replace(' unselected', '');
              domElems.Button[nextIndex].className = clsNameSelected
            elem.className += " selected";
              ScrollTo ('.selected',{offset:0,align: 'middle',duration: 1500});
            }
          } else {
            let clsNameUnselected = domElems.Button[nextIndex].className;
            clsNameUnselected = clsNameUnselected.replace(' selected', '');
            domElems.Button[nextIndex].className = clsNameUnselected;
            domElems.Button[nextIndex].className += " unselected"
            nextIndex = nextIndex - 1;
            let elem = domElems.Button[nextIndex];
            if((nextIndex%noOfButtonsInCarousal) <= this.NUMBER_OF_ITEMS) {
              let clsNameSelected = domElems.Button[nextIndex].className;
              clsNameSelected = clsNameSelected.replace(' unselected', '');
              domElems.Button[nextIndex].className = clsNameSelected
              elem.className += " selected";
              ScrollTo ('.selected',{offset:0,align: 'middle',duration: 1500});
            }
          } }else if(nextIndex === 0){
          let clsNameUnselected = domElems.Button[nextIndex].className;
          clsNameUnselected = clsNameUnselected.replace(' selected', '');
          domElems.Button[nextIndex].className = clsNameUnselected;
          domElems.Button[nextIndex].className += " unselected"
              nextIndex = this.NUMBER_OF_ITEMS;
              let elem = domElems.Button[nextIndex];
              let clsNameSelected = domElems.Button[nextIndex].className;
              clsNameSelected = clsNameSelected.replace(' unselected', '');
              domElems.Button[nextIndex].className = clsNameSelected
              elem.className += " selected";
              ScrollTo ('.selected',{offset:0,align: 'middle',duration: 1500});
            } else {
              let clsNameUnselected = domElems.Button[nextIndex].className;
              clsNameUnselected = clsNameUnselected.replace(' selected', '');
              domElems.Button[nextIndex].className = clsNameUnselected;
              domElems.Button[nextIndex].className += " unselected"
                nextIndex = nextIndex -1;
                let elem = domElems.Button[nextIndex];
                let clsNameSelected = domElems.Button[nextIndex].className;
                clsNameSelected = clsNameSelected.replace(' unselected', '');
                domElems.Button[nextIndex].className = clsNameSelected
                elem.className += " selected";
                ScrollTo ('.selected',{offset:0,align: 'middle',duration: 1500});
            }
        } else if (e.key === "Enter") {
          if (e.target.className==="input_feild" && document.getElementById('footerMsg').value !== "") {
            this.handleSoftRightKey();
          } else {
          let elem = domElems.Button[nextIndex];
          var properPayLoad1 = this.preparePayload(elem.id);
          var req = '"request":{"message":{ "text": "'+elem.value+'","quick_reply":{"payload": "' + properPayLoad1 + '"}}}';
          this.requestMumbailocalData(elem.value, properPayLoad1, req)
        }
      } else if (e.key === 'SoftLeft') {
          document.getElementById("footerMsg").blur();
          if (latestReqParam === this.state.request) {
            this.context.router.history.push("/");
          } else {
            this.requestMumbailocalData(undefined, undefined, req);
          }
        } else if(e.key === 'SoftRight'){
          this.handleSoftRightKey();
        } else if(e.key==='#'){
          document.getElementById("footerMsg").focus();
          setTimeout(function(){
            document.getElementById("footerMsg").value = "";
           }, 0);
        } else if(e.key==='*'){
        let MozActivity= window.MozActivity||null;
        if (MozActivity) {
          var voicetotext = new MozActivity({
					name: "voicetotext",
					data: {
						"mode":"Jio Assist"
					}
				});

				voicetotext.onsuccess = function() {
					console.log("voicetotext.result");
					var result = voicetotext.result;
					var tempresult = result.resultantData.text.split(' ').join('');
					if(!isNaN(tempresult)) {
						result.resultantData.text = tempresult;
						console.log("Inside PNR NUMBER Processing");
					}

					console.log("Result from GVA : "+result.resultantData.text)
					// START :: Handling Immediate Proccessing of REQUESTS

					document.getElementById('footerMsg').value  = result.resultantData.text;
					document.getElementById('footerMsg').blur();
				};

				voicetotext.onerror = function(){
					console.log('error text need to print');
				};
        }
      }
  },

  // Payload is modified according to required format.
  preparePayload(load) {
    return load.replace(/"/g, '\\\"');
  },

  // This function is used to het the DOM Elements of a particular tag.
  getDOMElements() {
			var mapping = {
					Button: 'button'
			};
			return Object.keys(mapping).reduce((a, x) => {
					a[x] = document.querySelectorAll(mapping[x]);
					return a;
			}, {});
	},

  render() {
  return (
  <div>
    <Header data={this.props.match.params.id.split(",")[1]}/>
    <Container  data = {this.state.data}
                    message = {this.state.message}
                    updated = {this.state.updated}
                    image = {this.state.image}
                    isQuickReply = {this.state.isQuickReply}
                    isCarousal = {this.state.isCarousal}
                    isTextView = {this.state.isTextView}
                    requestUrl = {this.state.requestUrl}
                    address = {this.state.address}
                    prov = {this.state.prov}
                    requestParameter = {this.state.requestParameter}
                    provider = {this.state.provider}
                    request = {this.state.request}
                    onClick = {this.requestMumbailocalData}
                    addContact ={ this.updateNoOfButtoninCarousal }
                    />
    <Footer onClick = {this.requestMumbailocalData}/>
  </div>
  )
  },

  });

  Chat.contextTypes = {
   router: React.PropTypes.object
 };

  export default withRouter (Chat);
