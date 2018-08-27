import React, { Component } from 'react';
import Child from './Child';

const Parent =(props)=> {
	return (
      <div>
          {/* <Child {...props}/> */}
		  <Child doWhatever={props.changeComponentFirst} title={props.title}/>
		  <Child doWhatever={props.changeComponentSecond} title={props.title}/>
      </div>
    );
}


export default Parent;
