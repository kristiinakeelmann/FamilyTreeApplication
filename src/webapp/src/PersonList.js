import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
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
    const {persons, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <h3>Family Tree</h3>
          <Table className="mt-4">
            <thead>
            <tr>
              <th width="20%">First name</th>
              <th width="20%">Last name</th>
              <th width="20%">Sex</th>
              <th width="20%">Birthday</th>
            </tr>
            </thead>
            <tbody>
           <FilterSearch content={persons}/>
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default PersonList;