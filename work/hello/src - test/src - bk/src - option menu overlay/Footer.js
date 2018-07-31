import React from 'react';

class Footer extends React.Component{	
  render(){
    return (		
      <footer className='App-footer'>
		<div className="footerMsg" id="footerMsgBg"> <input id='footerMsg' placeholder='Long Press CSK to speak' className='input_feild' /> </div>
        <div className='textmsg'>
          <div className='lsk'> Home </div>
          <div className="csk"> &nbsp; </div>
          <div id='rsk' className='rsk'> Options </div>
        </div>
      </footer>
    )
  }
}
export default Footer