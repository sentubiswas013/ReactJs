import React, { Component } from 'react';
import PropTypes from 'prop-types';
import KeyPressHighlight from './KeyPressHighlight';

class KeyboardNavigation extends Component {
  static propTypes = {
    next: PropTypes.func,
    previous: PropTypes.func,
  };

  state = {
    active: null,
  };

  componentWillMount() {
    window.addEventListener('keydown', this.navigate);
  }

  componentWillUnmount() {
    window.removeEventListener('keydown', this.navigate);
  }

  navigate = ({ keyCode }) => {
    switch (keyCode) {
      case 68:
        this.setState({ active: 'next' });
        this.props.next();
        break;
      case 65:
        this.setState({ active: 'previous' });
        this.props.previous();
        break;
      default:
    }
  };

  render() {
    return <KeyPressHighlight active={this.state.active} />;
  }
}

export default KeyboardNavigation;
