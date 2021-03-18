'use strict'

let stompClientSolic
let solicitationId


//Solicitation

const connectSolic = (event) => {
    solicitationId = document.querySelector('#solicitation_id').value.trim()

    if (solicitationId) {
        const login = document.querySelector('#follow-up')
        login.classList.add('hide')

        const chatPage = document.querySelector('#solicitation-page')
        chatPage.classList.remove('hide')

        const socket = new SockJS('http://localhost:8090/solicitation')
        stompClientSolic = Stomp.over(socket)
        stompClientSolic.connect({}, onConnectedSolic, onError)
    }
    event.preventDefault()
}

const onConnectedSolic = () => {
    let socketUri = '/topic/'+solicitationId;
    stompClientSolic.subscribe(socketUri, onMessageReceivedSolic)
    const status = document.querySelector('#status')
    status.className = 'hide'
}

const onMessageReceivedSolic = (payload) => {
    const solicitation = JSON.parse(payload.body);
    const actual = document.getElementById('solicitation'+solicitation.id)
    if(!actual){
        const body = document.createElement('div')
        body.className = 'card-body card-body-solic'
        body.id = 'solicitation'+solicitation.id
            const flexBox = document.createElement('div')
                flexBox.className = 'd-flex justify-content-end mb-4'
                flexBox.appendChild(getAvatar("id",solicitation.id))
                flexBox.appendChild(getAvatar("catcher_code",solicitation.catcher_code))
                flexBox.appendChild(getAvatar("description",solicitation.description))
                flexBox.appendChild(getAvatar("percent",solicitation.percent))
        body.appendChild(flexBox);
        const realNode = document.querySelector('#solicitation-card')
        realNode.appendChild(body);
    }else{
        document.querySelector("#solicitation"+solicitation.id+" > div > div:nth-child(4) > input").value = solicitation.percent;
    }  
}

const getAvatar = (key,value) => {
    const container = document.createElement('div')
    container.style = "text-align: center"        
        const spanKey = document.createElement('span')
        spanKey.className = 'card-span-solic'
        spanKey.textContent = key        
        const inputValue = document.createElement('input')
        inputValue.value=value
    container.appendChild(spanKey)
    container.appendChild(inputValue)
    return container;
}

const followUpControls = document.querySelector('#follow-up-controls')
followUpControls.addEventListener('submit', connectSolic, true)
