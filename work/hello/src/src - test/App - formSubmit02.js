var TextBox = React.createClass({
    render: function() {
        return (
			<div className="field">
				<label>{this.props.label}</label>
				<input className='form-control' 
					   name={this.props.name}
					   type='text' 
					   value={this.props.value}
					   onChange={this.props.onChange}/>
			</div>
        );
    }
});

var ExampleForm = React.createClass({
    getInitialState: function () {
        return { 
		form: { ISIN: 'abc123', nameOfSecurity: 'Bond', baseCurrency: 'EUR', issuer: 'ECB', typeOfSecurity: '001', nominalPrice: '0', interestRate: '0.5', effectiveDate: 'dd/mm/yyyy', redemptionDate: 'dd/mm/yyyy'}}
    },
    onChange: function(event) {
        this.state.form[event.target.name] = event.target.value;
        this.setState({form: this.state.form});
    },
    onSubmit: function(event) {
        event.preventDefault();
        alert('Form submitted. ISIN: ' +
            this.state.form.ISIN +
            ', nameOfSecurity: ' +
            this.state.form.nameOfSecurity);
            
    },
    render: function() {
       var self = this;
       return (
            <form onSubmit={this.onSubmit}>
                <TextBox name='ISIN' 
                    label = {'ISIN'} 
                    value={this.state.form.ISIN}
                    onChange={this.onChange}/>
                <TextBox name='nameOfSecurity'
                    label = {'Name of Security'} 
                    value={this.state.form.nameOfSecurity}
                    onChange={this.onChange}/>
                <TextBox name='baseCurrency'
                    label = {'Base Currency'} 
                    value={this.state.form.baseCurrency}
                    onChange={this.onChange}/>
                <TextBox name= 'issuer'
                    label = {'Issuer'}
                    value = {this.state.form.issuer}
                    onChange={this.onChange}/>
                <TextBox name= 'typeOfSecurity'
                    label = {'Type of Security'}
                    value = {this.state.form.typeOfSecurity}
                    onChange = {this.onChange}/>
                <TextBox name = 'nominalPrice'
                    label = {'Nominal Price'}
                    value = {this.state.form.nominalPrice}
                    onChange = {this.onChange}/>
                <TextBox name = 'interestRate'
                    label = {'Interest Rate'}
                    value = {this.state.form.interestRate}
                    onChange = {this.onChange}/>
                <TextBox name =  'effectiveDate'
                    label = {'Effective Date'}
                    value = {this.state.form.effectiveDate}
                    onChange = {this.onChange}/>
                <TextBox name = 'redemptionDate'
                    label = {'Redemption Date'}
                    value = {this.state.form.redemptionDate}
                    onChange = {this.onChange}/>
                <button className='btn btn-success' 
                	type='submit'>Submit</button>
            </form>
        );
    }
});

ReactDOM.render(
    <ExampleForm/>,
    document.getElementById('view'));