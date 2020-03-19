import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import PersonList from './PersonList';
import PersonEdit from './PersonEdit';

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Home}/>
                    <Route path='/persons' exact={true} component={PersonList}/>
                    <Route path='/person/:id' component={PersonEdit}/>
                </Switch>
            </Router>
        )
    }
}

export default App;