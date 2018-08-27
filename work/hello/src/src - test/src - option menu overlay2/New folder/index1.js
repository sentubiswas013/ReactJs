import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import ServiceApi from './serviceApi';
import registerServiceWorker from './registerServiceWorker';

class App extends React.Component {
	render(){
		return(
			<div> 
			{
				//console.log(ServiceApi)
				ServiceApi.userinfo.map((user, index)=>{
					return(
						<div key={index}> {user.address.geo.lat} </div>
					)
				})
			}
			</div>
		)
	}
}

ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();
