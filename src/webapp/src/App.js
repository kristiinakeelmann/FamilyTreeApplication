import React, {Component} from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import PersonList from './PersonList';
import PersonEdit from './PersonEdit';
import RelationEdit from './RelationEdit';
import FamilyTreeView from "./FamilyTreeView";
import StatisticsView from "./StatisticsView";
import PageNotFound from "./PageNotFound";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {};
    }

    handleRelationSuccess() {
        const relationSuccessMessage = "Relation successfully added!";
        this.setState({successMessage: relationSuccessMessage});
    }

    handlePersonSuccess() {
        const personSuccessMessage = "Person successfully added!";
        this.setState({successMessage: personSuccessMessage});
    }

    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={PersonList}/>
                    <Route path='/persons' exact={true} component={() => <PersonList successMessage={this.state.successMessage}/>}/>
                    <Route path='/person/:id' exact={true} component={() => <PersonEdit handleSuccess={() => {this.handlePersonSuccess()}}/>}/>
                    <Route path='/relation/:id' exact={true} component={() => <RelationEdit handleSuccess={() => {this.handleRelationSuccess()}}/>}/>
                    <Route path='/familytree/:id' exact={true} component={FamilyTreeView}/>
                    <Route path='/statistics' exact={true} component={StatisticsView}/>
                    <Route component={PageNotFound}/>
                </Switch>
            </Router>
        )
    }
}

export default App;