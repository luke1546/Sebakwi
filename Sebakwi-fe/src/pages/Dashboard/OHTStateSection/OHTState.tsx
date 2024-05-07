import React from 'react';
import * as Styled from './OHTState_style';

export default function OHTState() {
    return (
        <div>
            <Styled.StatusBox>
                <div>정비 중</div>
                <div>5</div>
            </Styled.StatusBox>
            <Styled.StatusBox>
                <div>운행 중</div>
                <div>545</div>
            </Styled.StatusBox>
            <Styled.StatusBox>
                <div>총 대수</div>
                <div>550</div>
            </Styled.StatusBox>
        </div>
    );
}