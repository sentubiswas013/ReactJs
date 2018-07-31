import React from 'react';
class Footer extends React.Component {
    componentWillMount () {
        document.addEventListener('keydown', this.keydownHandler);       
    }
    keydownHandler(e) {
       //console.log(e);
       if(e.key == 'ArrowRight') {
            alert("ASDsa");
       }
    }
    render() {
        return (
            <div className="footerHome">
                <span> Cancel </span>
                <span id="footerHomeMiddle"> ok  </span>
                <span> Option </span>
            </div>
        )
    }
} 

export default Footer;
