import { TableData } from 'types';
import * as Styled from './ModalTable_style';

interface ModalTableProps {
  data: TableData[];
}


export default function ModalTable(props : ModalTableProps) {
  const { data } = props;

  return (
    <table>
      <tbody>
        {data.map((row, index) => (
          <tr key={index}>
            <td>{row.item}</td>
            <td>
              {typeof row.value === 'boolean' ? (
                <Styled.CheckBoxInput
                  type="checkbox"
                  checked={row.value}
                  readOnly
                />
              ) : (
                row.value
              )}
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}




