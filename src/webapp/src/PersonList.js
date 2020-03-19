import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

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

    const personList = persons.map(person => {
      return <tr key={person.id}>
              <td>{person.firstName}</td>
              <td>{person.lastName}</td>
              <td>{person.sex}</td>
              <td>{person.dateOfBirth}</td>

        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/person/" + person.id}>Edit</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/person/new">Add Person</Button>
          </div>
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
            {personList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default PersonList;