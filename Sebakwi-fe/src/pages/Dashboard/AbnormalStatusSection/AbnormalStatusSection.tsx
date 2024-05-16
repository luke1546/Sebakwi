import { Abnormal, TableProps, AbnormalStatusSectionProps } from 'types';
import 'react-toastify/dist/ReactToastify.css';
import * as Styled from './AbnormalStatusSection_style';


// 비정상 수 컴포넌트
function AbnormalDetail({ title, count }: Abnormal) {
  return (
    <Styled.AbBox>
      <Styled.AbBoxTitle>{title}</Styled.AbBoxTitle>
      <Styled.AbBoxCount>{count}</Styled.AbBoxCount>
    </Styled.AbBox>
  );
}

// 표
function WheelTable({ data }: TableProps) {
  return (
    <Styled.TableContainer>
      <Styled.Table>
        <Styled.TableHead>
          <Styled.TableTr>
            <Styled.TableTh>휠 번호</Styled.TableTh>
            <Styled.TableTh>OHT 번호</Styled.TableTh>
            <Styled.TableTh>위치</Styled.TableTh>
            <Styled.TableTh>상태</Styled.TableTh>
          </Styled.TableTr>
        </Styled.TableHead>
        <Styled.TableBody>
          {data ? (
            data.map((item, index) => {
              // 상태를 배열로 관리
              let statuses = [];
              if (item.stamp) statuses.push('찍힘');
              if (item.crack) statuses.push('크랙');
              if (item.peeling) statuses.push('박리');

              // 상태 배열을 문자열로 합치기
              let statusString = statuses.join(', ');

              return (
                <tr key={index}>
                  <Styled.TableTd>{item.wheelNumber}</Styled.TableTd>
                  <Styled.TableTd>{item.ohtNumber}</Styled.TableTd>
                  <Styled.TableTd>{item.position}</Styled.TableTd>
                  <Styled.TableTd>{statusString}</Styled.TableTd>
                </tr>
              );
            })
          ) : (
            <tr></tr>
          )}
        </Styled.TableBody>
      </Styled.Table>
    </Styled.TableContainer>
  );
}


export default function AbnormalStatusSection(props : AbnormalStatusSectionProps) {
  const { data } = props;

  return (
    <Styled.AbContainer>
      <Styled.AbTop>
        <AbnormalDetail title="찍힘" count={data ? data.count.stamp : 0}></AbnormalDetail>
        <AbnormalDetail title="크랙" count={data ? data.count.crack : 0}></AbnormalDetail>
        <AbnormalDetail title="박리" count={data ? data.count.peeling : 0}></AbnormalDetail>
        <AbnormalDetail title="합계" count={data ? data.count.total : 0}></AbnormalDetail>
      </Styled.AbTop>
      <WheelTable data={data?.wheelList} />
    </Styled.AbContainer>
  );
}
