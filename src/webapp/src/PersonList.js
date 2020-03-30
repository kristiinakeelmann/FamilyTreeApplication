import React, { Component } from 'react';
import { Container, Spinner, Alert } from 'reactstrap';
import AppNavbar from './AppNavbar';
import FilterSearch from './FilterSearch.js';

class PersonList extends Component {

  constructor(props) {
    super(props);
    this.state = {
    persons: [],
    isLoading: true,
    successMessage: {},
    }
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('/api/persons')
      .then(response => response.json())
      .then(data => this.setState({persons: data, isLoading: false}));
  }

  render() {
    const persons = this.state.persons;
    const isLoading = this.state.isLoading;
    const successMessage = this.props.successMessage;

    if (isLoading) {
      return <Spinner style={{ width: '3rem', height: '3rem' }} type="grow"/>;
    }

    return (
      <div>
        <AppNavbar/>
        <Container>
          {successMessage ? <Alert color="success"> {successMessage} </Alert> : null}
           <FilterSearch content={persons}/>
        </Container>
      </div>
    );
  }
}

export default PersonList;