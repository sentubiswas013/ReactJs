import React from 'react';
import ReactDom from 'react-dom';
import Modal from 'react-modal';

class Popup extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			isOpen:false
		}
		this.oepnModal = this.oepnModal.bind(this);
	}
	componentWillMount(){
		Modal.setAppElement('body');
	}
	oepnModal(){
		this.setState({
			isOpen:!this.state.isOpen	
		})
	}
	render() {
		return (
			<div>
				<button onClick={this.oepnModal}> Open Modal </button>
				<Modal isOpen={this.state.isOpen} onRequestClose={this.oepnModal}> 
					<button onClick={this.oepnModal}> Close </button>
				 </Modal>
			</div>
		)
	}
}

ReactDom.render(<Popup/>, document.getElementById("root"));
