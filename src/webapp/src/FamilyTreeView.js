import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label, Spinner} from 'reactstrap';
import AppNavbar from './AppNavbar';
import FamilyTree from "./FamilyTree";


class FamilyTreeView extends Component {

    constructor(props) {
        super(props);
        this.state = {
            persons: [],
            isLoading: true,
            showComponent: false
        };

        this.handlePersonChange = this.handlePersonChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }


    componentDidMount() {
        this.setState({isLoading: true});
        this.setState({selectedPersonId: 1});

        fetch('/api/persons')
            .then(response => response.json())
            .then(data => this.setState({persons: data, isLoading: false}));
    }


    handlePersonChange(event) {
        const selectedPersonId = event.target.value;
        this.setState({selectedPersonId: selectedPersonId});
    }


    handleSubmit(event) {
        event.preventDefault();
        this.setState({showComponent: true});
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

        const title = <h2>{'Generate Family Tree'}</h2>;

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="exampleSelect">Select Person</Label>
                        <Input type="select" name="selectPerson" id="exampleSelect"
                               value={this.state.selectedPersonId ? this.state.selectedPersonId : null}
                               onChange={this.handlePersonChange}>
                            {optionItems}
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Generate</Button>{' '}
                        <Button color="secondary" tag={Link} to="/persons">Cancel</Button>
                    </FormGroup>
                    <FormGroup>
                        {this.state.showComponent ? <FamilyTree selectedPersonId={this.state.selectedPersonId}/> : null}
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(FamilyTreeView);