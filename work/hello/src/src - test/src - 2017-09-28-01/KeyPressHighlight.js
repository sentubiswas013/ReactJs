import React, { Component } from 'react';
import PropTypes from 'prop-types';
import './keypress.css';

export default class KeyPressHighlighter extends Component {
  state = {
    highlighted: false,
  };

  componentWillReceiveProps({ active }) {
    this.setState({ highlighted: true });
    setTimeout(() => {
      this.setState({ highlighted: false });
    }, 100);
  }

  render() {
    return (
      <div className="keys">
        <div
          className="key"
          data-highlighted={
            this.state.highlighted && this.props.active === 'previous'
          }
        >
          ◀
        </div>
        <div
          className="key"
          data-highlighted={
            this.state.highlighted && this.props.active === 'next'
          }
        >
          ►
        </div>
      </div>
    );
  }
}
