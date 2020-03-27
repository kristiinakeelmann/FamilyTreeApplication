import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import PersonList from './PersonList';
import PersonEdit from './PersonEdit';
import RelationEdit from './RelationEdit';
import FamilyTreeView from "./FamilyTreeView";
import StatisticsView from "./StatisticsView";



class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={PersonList}/>
                    <Route path='/persons' exact={true} component={PersonList}/>
                    <Route path='/person/:id' component={PersonEdit}/>
                    <Route path='/relation/:id' component={RelationEdit}/>
                    <Route path='/familytree/:id' component={FamilyTreeView}/>
                    <Route path='/statistics' component={StatisticsView}/>
                </Switch>
            </Router>
        )
    }
}

export default App;