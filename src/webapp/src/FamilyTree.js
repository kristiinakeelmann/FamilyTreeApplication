import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import {Card, Spinner} from 'reactstrap';
import FamilyTreeTemplate from './illustrations/familytree_template.png';


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
        let path = '/api/familytree/' + selectedPersonId;

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
        if (previousProps.selectedPersonId !== selectedPersonId) {
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
        const sisters = familyMembers.sisters;
        const brothers = familyMembers.brothers;
        const children = familyMembers.children;

        return <div>
            <Card>
                    <div style={{position: "relative", width: "1000px"}}>
                    <img src={FamilyTreeTemplate} alt="Family Tree" style={{width: "1100px", height: "1000px"}}/>
                    <div style={{position: "absolute", top: "630px", left: "480px", color: "purple", fontWeight: "bold"}}> {selectedPersonName} </div>
                    <div style={{position: "absolute", top: "530px", left: "400px"}}> {motherName} </div>
                    <div style={{position: "absolute", top: "530px", left: "600px"}}> {fatherName} </div>
                    <div style={{position: "absolute", top: "430px", left: "150px"}}> {motherMotherName} </div>
                    <div style={{position: "absolute", top: "430px", left: "300px"}}> {motherFatherName} </div>
                    <div style={{position: "absolute", top: "430px", left: "750px"}}> {fatherMotherName} </div>
                    <div style={{position: "absolute", top: "430px", left: "900px"}}> {fatherFatherName} </div>
                    <div style={{position: "absolute", top: "630px", left: "150px"}}> {sisters.length  > 0 && sisters[0]} </div>
                    <div style={{position: "absolute", top: "630px", left: "350px"}}> {sisters.length  > 1 && sisters[1]}  </div>
                    <div style={{position: "absolute", top: "630px", left: "50px"}}> {sisters.length  > 2 && sisters[2]}  </div>
                    <div style={{position: "absolute", top: "630px", left: "700px"}}> {brothers.length  > 0 && brothers[0]} </div>
                    <div style={{position: "absolute", top: "630px", left: "900px"}}> {brothers.length  > 1 && brothers[1]} </div>
                    <div style={{position: "absolute", top: "730px", left: "480px"}}> {children.length  > 0 && children[0]} </div>
                    <div style={{position: "absolute", top: "780px", left: "480px"}}> {children.length  > 1 && children[1]} </div>
                    <div style={{position: "absolute", top: "830px", left: "480px"}}> {children.length  > 2 && children[2]} </div>
                    <div style={{position: "absolute", top: "880px", left: "480px"}}> {children.length  > 3 && children[3]} </div>
                    <div style={{position: "absolute", top: "930px", left: "480px"}}> {children.length  > 4 && children[4]} </div>
                    </div>
            </Card>
        </div>
    }
}

export default withRouter(FamilyTree);