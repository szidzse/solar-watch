export function formatLocalDateTime(localDateTime) {
    const date = new Date(localDateTime);
    if (isNaN(date.getTime())) {
        throw new Error("Invalid date format");
    }
  
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
  
    return `${hours}:${minutes}:${seconds}`;
}