import React from 'react';
import { render } from 'react-dom';
import history from "./history.js";

import { Router, Route } from "react-router-dom";


const Test = () => <div>Welcome to /test</div>;

const App = () => (
  <div>
    <Router history={history}>
      <Route path="/test" component={Test} />
    </Router>
    <button onClick={() => history.push('/test')}>goto /test</button>    
    <button onClick={() => history.push('/')}>goto /</button>    
  </div>
);



render(<App />, document.getElementById('root'));
