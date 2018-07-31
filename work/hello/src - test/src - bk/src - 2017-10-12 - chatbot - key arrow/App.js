import React from 'react';
import SlickSlider from 'react-slick'
import ScrollTo from 'scroll-to-element'
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import './App.css';
import logo from './logo_irctc.jpg'
import logo1 from './logo.svg';


class SimpleSlider extends React.Component {


  componentWillMount () {
    document.addEventListener("keydown", this.handleKeyDown);

  }

  handleKeyDown(e) {
	console.log(e);
    console.log("handleKeyDown");
    //ScrollTo ('.slick-slide.slick-active.selected',{offset:0,align: 'middle',duration: 1500});
	var slkSlder_Vert_Length = document.querySelectorAll(".slick-slider");
	var slkSlder_Horz_Length = document.querySelectorAll(".slick-slide");
	
	console.log(slkSlder_Vert_Length+slkSlder_Horz_Length);
	
	if(e.key == "ArrowDown"){
		console.log(slkSlder_Vert_Length);
		if(slkSlder_Vert_Length == 0){
			console.log("Hi");
		}else{
			console.log("Hello");
		}		
	}
	
	
	
  }

  shouldComponentUpdate (nextProps) {
    // TODO: add proper implementation that compares objects
    return false;
  }

  render () {
    var settings = {
      dots: false,
      infinite: false,
      speed: 500,
      slidesToShow: 4,
      slidesToScroll: 1,
    };
    console.log('slider render');
    return (
      <div>
        <div className="carousalHeader">
          New Launch
        </div>
        <SlickSlider {...settings}>
          <div id="irctc" style={{overflow:"hidden !important"}}>
            <div className = "card irctc">
              <div className = "cardImage">
                <img className ="image" src= {logo}/>
              </div>
              <div className = "cardTitle">
                IRCTC
              </div>
            </div>
          </div>
          <div>
            <div className = "card irctc">
              <div className = "cardImage">
                <img className ="image" src= {logo1}/>
              </div>
              <div className = "cardTitle">
                IRCTC
              </div>
            </div>
          </div>
          <div className="selected">
            <div className = "card irctc">
              <div className = "cardImage">
                <img className ="image" src= {logo}/>
              </div>
              <div className = "cardTitle">
                IRCTC
              </div>
            </div>
          </div>
          <div>
            <div className = "card irctc">
              <div className = "cardImage">
                <img className ="image" src= {logo1}/>
              </div>
              <div className = "cardTitle">
                IRCTC
              </div>
            </div>
          </div>
        </SlickSlider>
        <div className="carousalHeader">
          Most Popular
        </div>
        <SlickSlider {...settings}>
          <div id="local" style={{overflow:"hidden !important"}}>
            <div className = "card local">
              <div className = "cardImage">
                <img className ="image" src= {logo}/>
              </div>
              <div className = "cardTitle">
                IRCTC
              </div>
            </div>
          </div>
          <div>
            <div className = "card local">
              <div className = "cardImage">
                <img className ="image" src= {logo1}/>
              </div>
              <div className = "cardTitle">
                IRCTC
              </div>
            </div>
          </div>
          <div className="selected">
            <div className = "card local">
              <div className = "cardImage">
                <img className ="image" src= {logo}/>
              </div>
              <div className = "cardTitle">
                IRCTC
              </div>
            </div>
          </div>
          <div>
            <div className = "card local">
              <div className = "cardImage">
                <img className ="image" src= {logo1}/>
              </div>
              <div className = "cardTitle">
                IRCTC
              </div>
            </div>
          </div>
        </SlickSlider>
      </div>
    )
  }
}



export default SimpleSlider;
