import React from 'react';
import { render } from 'react-dom';
import { createStore, } from 'redux';
import { connect, Provider, } from 'react-redux';
import { createSelector } from 'reselect';

/// http://54.158.192.232:3000/data

const productList = [
  {
    name: 'Fozzy Bear',
    price: 20,
  },
  {
    name: 'Kermit',
    price: 25,
  },
  {
    name: 'Miss Piggy',
    price: 25,
  },
];

const productListReducer = (state = {
  products: productList,
  filter: '',
}, action) => {
  switch (action.type) {
    case 'UPDATE_FILTER':
      return {
        ...state,
        filter: action.filter,
      };
    default:
      return state;
  }
};

const getFilteredProducts = createSelector(
  [ 
  	({ filter }) => filter,
  	({ products }) => products,
  ],
  (filter, products) => {
    console.log(arguments);
    if (filter === '') {
      return products;
    }
    const fre = new RegExp(filter, 'i');
    return products.filter(p => p.name.match(fre));
  }
)

const store = createStore(productListReducer);

const FilterInput = ({ filter, onChange, }) => (
  <input
    type="text"
    text={filter}
    onChange={(evt) => onChange(evt.target.value)}
    />
);

const ConnectedFilter = connect(
 ({ filter }) => ({ filter }),
 (dispatch) => ({
   onChange(filter) {
     dispatch({
       type: 'UPDATE_FILTER',
       filter,
     });
   },
 }),
)(FilterInput);

const ProductsList = ({ filteredProducts, }) => (
  <div>
    {filteredProducts.map((product, index) => (
    	<div key={index}>
        	{product.name} - ${product.price}
        </div>
    ))}
  </div>
);

const ConnectedProductsList = connect(
  (state, props) => ({
    filteredProducts: getFilteredProducts(state, props)
  }),
)(ProductsList);

const App = () => (
  <Provider store={store}>
    <div>
	  <ConnectedFilter />
      <ConnectedProductsList />
    </div>
  </Provider>
);

render(<App />, document.getElementById('root'));
