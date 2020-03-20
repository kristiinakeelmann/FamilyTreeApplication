import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from './AppNavbar';

class RelationEdit extends Component {

    constructor(props) {
        super(props);
        this.state = {
            persons: [],
        };

        this.handlePersonChange = this.handlePersonChange.bind(this);
        this.handleMotherChange = this.handleMotherChange.bind(this);
        this.handleFatherChange = this.handleFatherChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('/persons')
            .then(response => response.json())
            .then(data => this.setState({persons: data, isLoading: false}));
    }


    handlePersonChange(event) {
        this.setState({selectedPersonId: event.target.value});
    }

    handleMotherChange(event) {
        this.setState({selectedMotherId: event.target.value});
    }

    handleFatherChange(event) {
        this.setState({selectedFatherId: event.target.value});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const selectedPersonId = this.state.selectedPersonId;
        const selectedPerson = this.state.persons.find(person => person.id == selectedPersonId);
        selectedPerson.biologicalMotherId = this.state.selectedMotherId;
        selectedPerson.biologicalFatherId = this.state.selectedFatherId;

        let method = 'PUT';
        let path = '/person/' + selectedPersonId;

        await fetch(path, {
            method: method,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(selectedPerson),

        });

        this.props.history.push('/persons');
    }

    render() {
        const {persons, isLoading} = this.state;


        if (isLoading) {
            return <p>Loading...</p>;
        }

        let optionItems = persons
            .map(person => <option key={person.id}
                                   value={person.id}>{person.firstName + ' ' + person.lastName}</option>);

        let optionItemsMothers = persons
            .filter(person => person.sex === 'female')
            .map(person => <option key={person.id}
                                   value={person.id}>{person.firstName + ' ' + person.lastName}</option>);

        let optionItemsFathers = persons
            .filter(person => person.sex === 'male')
            .map(person => <option key={person.id}
                                   value={person.id}>{person.firstName + ' ' + person.lastName}</option>);

        const title = <h2>{'Add Relation'}</h2>;

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="exampleSelect">Select Person</Label>
                        <Input type="select" name="selectPerson" id="exampleSelect" onChange={this.handlePersonChange}>
                            {optionItems}
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        <Label for="exampleSelect">Select Biological Mother</Label>
                        <Input type="select" name="selectMother" id="exampleSelect" onChange={this.handleMotherChange}>
                            {optionItemsMothers}
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        <Label for="exampleSelect">Select Biological Father</Label>
                        <Input type="select" name="selectFather" id="exampleSelect" onChange={this.handleFatherChange}>
                            {optionItemsFathers}
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/persons">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(RelationEdit);