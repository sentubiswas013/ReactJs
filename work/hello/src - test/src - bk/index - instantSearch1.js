import React from 'react'
import '../assests/css/App.css'

let stationsData = ['IRCTC', 'Mumbai Local', 'Kolkata', 'Pune', 'Bangalore'];

class SearchComponent extends React.Component {
    constructor() {
        super();
        this.state = {
            list: stationsData,
            filteredList: [],
            searchString: ''
        }
        this.filterList = this.filterList.bind(this);
    }

    componentWillMount() {
        this.setState({filteredList: this.state.list});
    }

    filterList(value) {
        let searchValue = value.toLowerCase();
        let filteredList = this.state.list;

        filteredList = filteredList.filter(item => {
            return item.toLowerCase().search(searchValue) !== -1;
        });

        this.setState({filteredList: filteredList});
        this.setState({searchString: value});
    }

    render() {
        return (
            <div className="search-component">
                <SearchInput value={this.state.searchString} update={this.filterList} />
				<div className="recentSearch"> Recent Searces </div>
                <ul className="search-list">
                    {
						this.state.filteredList.map((item, index) => {
							return(
								<li filter={this.filterList} key={index}> {item} </li>
							)	
						})
					}
                </ul>
            </div>
        )
    }
}

// Search input Component
class SearchInput extends React.Component {
    constructor() {
        super();
        this.onValueChange = this.onValueChange.bind(this);
    }
    onValueChange(event) {
        this.props.update(event.target.value);
    }
    render() {
        return (
            <div className="inputBox">
                <input
                    ref="input"
                    onChange={this.onValueChange}
                    value={this.props.value}
                    type="text"
                    placeholder="Type something..." />
            </div>
        )
    }
}

export default SearchComponent
