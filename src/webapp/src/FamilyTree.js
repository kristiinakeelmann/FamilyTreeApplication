import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import {Container, Spinner} from 'reactstrap';
import FamilyTreeTemplate from './familytreetemplate.svg';


class FamilyTree extends Component {

    constructor(props) {
        super(props);
        this.state = {
            familyMembers: {},
            isLoading: true,
            selectedPersonId: null
        };
    }

    getFamilyTree(selectedPersonId) {

        let method = 'GET';
        let path = '/familytree/' + selectedPersonId;

        fetch(path, {
            method: method,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        })
            .then(response => response.json())
            .then(data => this.setState({familyMembers: data, isLoading: false}));
    }


    componentDidMount() {
        const {selectedPersonId} = this.props;
        this.getFamilyTree(selectedPersonId);
    }


    componentDidUpdate(previousProps) {
        const selectedPersonId = this.props.selectedPersonId;
        if (previousProps.selectedPersonId != selectedPersonId) {
            this.getFamilyTree(selectedPersonId);
        }
    }


    render() {
        const isLoading = this.state.isLoading;
        const familyMembers = this.state.familyMembers;

        if (isLoading) {
            return <Spinner style={{width: '3rem', height: '3rem'}} type="grow"/>;
        }

        const selectedPersonName = familyMembers.selectedPersonName;
        const motherName = familyMembers.motherName;
        const fatherName = familyMembers.fatherName;
        const motherMotherName = familyMembers.motherMotherName;
        const motherFatherName = familyMembers.motherFatherName;
        const fatherMotherName = familyMembers.fatherMotherName;
        const fatherFatherName = familyMembers.fatherFatherName;
        const children = familyMembers.children;

        let firstSister;
        let secondSister;
        const sisters = familyMembers.sisters;
        if (sisters.length > 0) {
            firstSister = sisters[0];
        }
        if (sisters.length > 1) {
            secondSister = sisters[1];
        }

        let firstBrother;
        let secondBrother;
        const brothers = familyMembers.brothers;
        if (brothers.length > 0) {
            firstBrother = brothers[0];
        }
        if (brothers.length > 0) {
            secondBrother = brothers[1];
        }

        return <div>
            <Container>
                <div style={{position: "relative", width: "1500px"}}>
                    <img src={FamilyTreeTemplate} alt="Family Tree"/>
                    <div style={{position: "absolute", top: "290px", left: "580px"}}> {selectedPersonName} </div>
                    <div style={{position: "absolute", top: "200px", left: "600px"}}> {motherName} </div>
                    <div style={{position: "absolute", top: "200px", left: "750px"}}> {fatherName} </div>
                    <div style={{position: "absolute", top: "115px", left: "250px"}}> {motherMotherName} </div>
                    <div style={{position: "absolute", top: "115px", left: "400px"}}> {motherFatherName} </div>
                    <div style={{position: "absolute", top: "115px", left: "1000px"}}> {fatherMotherName} </div>
                    <div style={{position: "absolute", top: "115px", left: "1150px"}}> {fatherFatherName} </div>
                    <div style={{position: "absolute", top: "290px", left: "250px"}}> {firstSister} </div>
                    <div style={{position: "absolute", top: "290px", left: "400px"}}> {secondSister} </div>
                    <div style={{position: "absolute", top: "290px", left: "750px"}}> {firstBrother} </div>
                    <div style={{position: "absolute", top: "290px", left: "930px"}}> {secondBrother} </div>
                    <div style={{position: "absolute", top: "390px", left: "500px"}}> {children} </div>
                </div>
            </Container>
        </div>
    }
}

export default withRouter(FamilyTree);