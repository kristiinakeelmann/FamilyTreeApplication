import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label, Alert, Spinner} from 'reactstrap';
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
        this.setState({selectedPersonId: 1});
        this.setState({selectedMotherId: 2});
        this.setState({selectedFatherId: 3});

        fetch('/persons')
            .then(response => response.json())
            .then(data => this.setState({persons: data, isLoading: false}));
    }

    getPerson(id) {
        return this.state.persons.filter(person => person.id == id)[0];
    }


    handlePersonChange(event) {
        const selectedPersonId = event.target.value;
        this.setState({selectedPersonId: selectedPersonId});
        if (selectedPersonId) {
            this.setState({selectedMotherId: this.getPerson(selectedPersonId).biologicalMotherId})
            this.setState({selectedFatherId: this.getPerson(selectedPersonId).biologicalFatherId})
        }
    }

    handleMotherChange(event) {
        this.setState({selectedMotherId: event.target.value});
    }

    handleFatherChange(event) {
        this.setState({selectedFatherId: event.target.value});
    }

    getErrors() {

        function getBirthYear(dateOfBirth) {
            const parts = dateOfBirth.split('-');
            const fullDate = new Date(parts[0], parts[1] - 1, parts[2]);
            const year = fullDate.toDateString().split(" ")[3]
            return year;
        }

        const selectedPerson = this.getPerson(this.state.selectedPersonId);
        if (selectedPerson == null) {
            return "Please select person";
        }
        const mother = this.getPerson(this.state.selectedMotherId);
        if (mother != null) {
            if (getBirthYear(selectedPerson.dateOfBirth) < getBirthYear(mother.dateOfBirth)) {
                return "Mother has to be older than child";
            }
            const father = this.getPerson(this.state.selectedFatherId);
            if (father != null) {
                if (getBirthYear(selectedPerson.dateOfBirth) < getBirthYear(father.dateOfBirth)) {
                    return "Father has to be older than child";
                }
            }
            return null;
        }
    }

    async handleSubmit(event) {
        event.preventDefault();
        const selectedPersonId = this.state.selectedPersonId;
        const selectedPerson = this.state.persons.find(person => person.id === selectedPersonId);
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
        this.props.handleSuccess();
    }

    render() {
        const persons = this.state.persons;
        const isLoading = this.state.isLoading;

        if (isLoading) {
            return <Spinner style={{width: '3rem', height: '3rem'}} type="grow"/>;
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
        const errorMessage = this.getErrors();

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                {errorMessage ? <Alert color="warning"> {errorMessage} </Alert> : null}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="exampleSelect">Select Person</Label>
                        <Input type="select" name="selectPerson"
                               value={this.state.selectedPersonId ? this.state.selectedPersonId : null}
                               onChange={this.handlePersonChange}>
                            <option value=""></option>
                            {optionItems}
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        <Label for="exampleSelect">Select Biological Mother</Label>
                        <Input type="select" name="selectMother"
                               value={this.state.selectedMotherId ? this.state.selectedMotherId : null}
                               onChange={this.handleMotherChange}>
                            <option value=""></option>
                            {optionItemsMothers}
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        <Label for="exampleSelect">Select Biological Father</Label>
                        <Input type="select" name="selectFather"
                               value={this.state.selectedFatherId ? this.state.selectedFatherId : null}
                               onChange={this.handleFatherChange}>
                            <option value=""></option>
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