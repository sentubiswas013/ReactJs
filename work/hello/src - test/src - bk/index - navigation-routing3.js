import React from 'react';
import { render } from 'react-dom';
import { BrowserRouter, Switch, Route, Link } from 'react-router-dom';
//import PlayerAPI from './api';

const App = () => (
  <div>
    <Switch>      
      <Route exact path='/' component={FullRoster}/>
      <Route path='/:number' component={Player}/>
    </Switch>
  </div>
)

const PlayerAPI = {
  players: [
    { number: 1, name: "Ben Blocker", position: "G" },
    { number: 2, name: "Dave Defender", position: "D" },
    { number: 3, name: "Sam Sweeper", position: "D" },
    { number: 4, name: "Matt Midfielder", position: "M" },
    { number: 5, name: "William Winger", position: "M" },
    { number: 6, name: "Fillipe Forward", position: "F" }
  ],
  all: function() { return this.players },
  get: function(id) {
    const isPlayer = p => p.number === id;
    return this.players.find(isPlayer);
  }
}

const FullRoster = () => (
  <div>
    <ul>
      {
        PlayerAPI.all().map(p => (
          <li key={p.number}>
            <Link to={`/${p.number}`}> {p.name} </Link>
          </li>
        ))
      }
    </ul>
  </div>
)

const Player = (props) => {
  const player = PlayerAPI.get(parseInt(props.match.params.number, 10))
  if (!player) {
    return <div>Sorry, but the player was not found </div>
  }
  return (
    <div>
      <h3>{player.name} (#{player.number})</h3>
      <h4>Position: {player.position} </h4>
      <Link to='/'>Back</Link>
    </div>
  )
}

render((
  <BrowserRouter>
    <App />
  </BrowserRouter>
), document.getElementById('root'));
