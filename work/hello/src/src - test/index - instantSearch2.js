import React from 'react'
import '../assests/css/App.css'

let classes = [ 'Temple Knight', 'Eva\'s Templar', 'Plains Walker', 'Wind Rider', 'Silver Ranger', 'Moonlight Sentinel', 'Spellsinger', 'Mystic Muse', 'Sword Singer', 'Sword Muse', 'Elemental Summoner', 'Elemental Master', 'Elven Elder', 'Eva\'s Saint'
];

class SearchComponent extends React.Component {
    constructor() {
        super();

        this.state = {
            list: classes,
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
        let searchItems = this.state.filteredList.map((item, index) => {
            return <SearchItem filter={this.filterList} key={index} value={item} />
        });

        return (
            <div className="search-component">
                <SearchInput value={this.state.searchString} update={this.filterList} />
                <ul className="search-list">
                    {searchItems}
                </ul>
            </div>
        )
    }
}

class SearchInput extends React.Component {
    constructor() {
        super();

        this.onValueChange = this.onValueChange.bind(this);
    }

    onValueChange(event) {
        // let input = React.findDOMNode(this.refs.input);
        
        this.props.update(event.target.value);
    }

    render() {
        return (
            <div className="search-input">
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

class SearchItem extends React.Component {
    onItemClick(value, event) {
        this.props.filter(value);
    }
    render() {
        return (
            <li onClick={this.onItemClick.bind(this, this.props.value)} >{this.props.value}</li>
        )
    }
}



export default SearchComponent
