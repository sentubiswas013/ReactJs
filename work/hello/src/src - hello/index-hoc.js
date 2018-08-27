import React from 'react';
import ReactDOM from 'react-dom';
import registerServiceWorker from './registerServiceWorker';

function wrapWithUser(Component) {
  var secretUserInfo = {
    name: 'Jack Franklin',
    favouriteColour: 'blue'
  }; 
  return function(props) {
    return <Component user={secretUserInfo} {...props} />
  }
}

var AppHeader = function(props) {
  if (props.user) {
    return <p>Logged in as {props.user.name}</p>;
  } else {
    return <p>You need to login</p>;
  }
};

var ConnectedAppHeader = wrapWithUser(AppHeader);
//const EnhancedComponent = higherOrderComponent(WrappedComponent);

ReactDOM.render(<ConnectedAppHeader />, document.getElementById('root'));
registerServiceWorker();
