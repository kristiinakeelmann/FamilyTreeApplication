import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table, Spinner } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import FilterSearch from './FilterSearch.js';

class PersonList extends Component {

  constructor(props) {
    super(props);
    this.state = {persons: [], isLoading: true};

  }


  componentDidMount() {
    this.setState({isLoading: true});

    fetch('/persons')
      .then(response => response.json())
      .then(data => this.setState({persons: data, isLoading: false}));
  }


  render() {
    const persons = this.state.persons;
    const isLoading = this.state.isLoading;

    if (isLoading) {
      return <Spinner style={{ width: '3rem', height: '3rem' }} type="grow"/>;
    }

    return (
      <div>
        <AppNavbar/>
        <Container>
           <FilterSearch content={persons}/>
        </Container>
      </div>
    );
  }
}

export default PersonList;