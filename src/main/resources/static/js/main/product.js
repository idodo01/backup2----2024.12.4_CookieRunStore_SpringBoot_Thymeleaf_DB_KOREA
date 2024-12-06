const mainImage = document.querySelector('.main-image-container > img');
const subImageButtons = document.querySelectorAll('.sub-image-container > button');
const mainImageSrc = mainImage.src;

const productOptionSelect = document.getElementById('product-option-select');
const productOptionUl = document.getElementById('product-option-ul');

/// 상품 이미지 변경하기
subImageButtons.forEach(subImageButton => {
    subImageButton.onmouseenter = () => {
        const imageTag = subImageButton.querySelector('img');
        mainImage.src = imageTag.src;
    }
    subImageButton.onmouseleave = () => {
        mainImage.src = mainImageSrc;
    }
});



/// 옵션 추가하기
productOptionSelect.onchange = () => {
    const optionValue = productOptionSelect.value;
    const plusStr = optionValue.lastIndexOf('+')
    const title = optionValue.substring(0, plusStr-1);
    const price = optionValue.slice(plusStr+1, -2);

    productOptionUl.insertAdjacentHTML(`beforeend`,
        `<li>
            <b>${title}</b>
            <label>
                <button type="button" class="amount-minus-btn" onclick="change_option_amount(this)"><i class="bi bi-dash-lg"></i></button>
                <input type="number" value="1">
                <button type="button" class="amount-plus-btn" onclick="change_option_amount(this)"><i class="bi bi-plus-lg"></i></button>
            </label>
            <button type="button" onclick="remove_option(this)"><i class="bi bi-x-square-fill"></i></button>
            <span>${price}원</span>
        </li>`
    )

}
/// 옵션 삭제하기
function remove_option(removeBtn){
    if(confirm('정말 삭제하시겠습니까?')){
        removeBtn.parentElement.remove();
    }
}
/// 옵션 수량 변경하기
function change_option_amount(amountButton){
    const numberInput = amountButton.parentElement.querySelector('input');
    if(amountButton.className.includes('minus')){
        numberInput.value = +numberInput.value - 1;
    }else{
        numberInput.value = +numberInput.value + 1;
    }
}



