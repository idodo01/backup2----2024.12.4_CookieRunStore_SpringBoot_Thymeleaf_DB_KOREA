const idInput = document.getElementById('id');
const pwInput = document.getElementById('password');
const pwReInput = document.getElementById('password-re');
const idCheckBtn = document.querySelector('.id-input-container button');
const idCheckInput = document.querySelector('input[name="id-check"]');
const idCheckValidInput = document.querySelector('input[name="id-check-valid"]');

const telHead = document.querySelector('.tel-input-container select');
const [telMiddle, telTail] = document.querySelectorAll('.tel-input-container input');
const [emailHead, emailTail] = document.querySelectorAll('.email-input-container input');
const emailSelect = document.querySelector('.email-input-container select');
const emailAuthBtn = document.querySelector('.email-input-container button');
const emailAuthConfirmInput = document.querySelector('.email-auth-input-container input');
const emailAuthConfirmBtn = document.querySelector('.email-auth-input-container button');
const [joinBtn, cancelBtn] = document.querySelectorAll('.join-btn-section > button');
const [idInputError, pwInputError, telInputError, emailInputError] = document.querySelectorAll('.error-container');
/************************************************************************/
// id 중복 체크 버튼 클릭 시
idCheckBtn.onclick = () => {
    const id = idInput.value;
    if(id.trim() === ''){
        alert('ID를 입력해주세요!');
        return;
    }
    fetch(`/user/id/${id}`)
        .then(response => {
            idCheckInput.value = id;
            idCheckValidInput.value = false;
            
            switch (response.status){
                case 200:
                    alert('해당 아이디는 사용 가능합니다^-^');
                    idCheckValidInput.value = true;
                    break;
                case 302:
                    alert('해당 아이디는 사용 불가능합니다ㅠㅠ');
                    break;
            }
        })
}


///// ********** 이메일
/// 이메일 주소 직접입력/자동 설정
emailSelect.onchange = () => {
    if(emailSelect.value === '직접입력'){
        emailTail.value = '';
        emailTail.readOnly = false;
    }else{
        emailTail.value = emailSelect.value;
        emailTail.readOnly = true;
    }
}
/// 이메일 인증 버튼 클릭 시
emailAuthBtn.onclick = () => {
    const email = `${emailHead.value}@${emailTail.value}`;
    fetch(`/email/auth`, {
        method: "POST",
        
        body: email
    });
}




///// ********** 입력 검사
function check_input_values(){
    idInputError.removeAttribute('active');
    pwInputError.removeAttribute('active');
    telInputError.removeAttribute('active');
    emailInputError.removeAttribute('active');

    //// 아이디 체크
    const id = idInput.value.trim();
    if(!((/^[a-z][0-9a-zA-Z]*$/.test(id)) && (id.length >= 4) && (id.length <= 15))){
        idInputError.setAttribute('active', '');
        return false;
    }
    //// 비밀번호 체크
    const pw = pwInput.value.trim();
    const pwRe = pwReInput.value.trim();
    const pwErrorSpan = pwInputError.querySelector('span');
    // 패스워드 잘못적음
    if(!/^[0-9a-zA-Z~!@#$%^&*()_=+.-]{4,10}$/.test(pw)){
        pwInputError.setAttribute('active', '');
        pwErrorSpan.textContent = '비밀번호 형식이 잘못되었습니다';
        return false;
    }
    // 패스워드 재입력과 다름
    if(pw !== pwRe){
        pwInputError.setAttribute('active', '');
        pwErrorSpan.textContent = '기존 입력한 비밀번호와 재입력한 비밀번호가 다릅니다';
        return false;
    }
    // 전화번호 검사
    const telHeadValue = telHead.value.trim();
    const telMiddleValue = telMiddle.value.trim();
    const telTailValue = telTail.value.trim();
    const tel = `${telHeadValue}-${telMiddleValue}-${telTailValue}`;
    if(!/^(010|011|017|019|018)-[0-9]{3,4}-[0-9]{4}$/.test(tel)){
        telInputError.setAttribute('active', '');
        return false;
    }

    // 이메일 검사
    const emailHeadValue = emailHead.value.trim();
    const emailTailValue = emailTail.value.trim();
    const email = `${emailHeadValue}@${emailTailValue}`;
    if(!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email)){
        emailInputError.setAttribute('active', '');
        return false;
    }


    return true;
}

///// ********** 회원가입 버튼 클릭 시
// 1) submit 버튼에서 event의 기본 동작 취소하기
// 2) form의 submit event의 기본 동작 취소하기
joinBtn.onclick = (event) => {
    // input 값들 중 하나라도 잘못 적었다면
    // if(!check_input_values()){
    //     event.preventDefault();
    // }
    
    const id = idCheckInput.value;
    const valid = idCheckValidInput.value;
    // 현재 회원가입하려고 하는 아이디가, 중복체크해서 사용한 아이디와 다르며
    // 중복체크를 통과하지 못했다면 회원가입을 시키면 안된다
    if(idInput.value !== id || valid === 'false'){
        alert('중복 체크 해주세요~');
        event.preventDefault();
    }
}





