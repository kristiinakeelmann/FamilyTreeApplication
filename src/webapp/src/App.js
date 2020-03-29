import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import PersonList from './PersonList';
import PersonEdit from './PersonEdit';
import RelationEdit from './RelationEdit';
import FamilyTreeView from "./FamilyTreeView";
import StatisticsView from "./StatisticsView";
import PageNotFound from "./PageNotFound";



class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={PersonList}/>
                    <Route path='/persons' exact={true} component={PersonList}/>
                    <Route path='/person/:id' exact={true} component={PersonEdit}/>
                    <Route path='/relation/:id' exact={true} component={RelationEdit}/>
                    <Route path='/familytree/:id' exact={true} component={FamilyTreeView}/>
                    <Route path='/statistics' exact={true} component={StatisticsView}/>
                    <Route component={PageNotFound}/>
                </Switch>
            </Router>
        )
    }
}

export default App;