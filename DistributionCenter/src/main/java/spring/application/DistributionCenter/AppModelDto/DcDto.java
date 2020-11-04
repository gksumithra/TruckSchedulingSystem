package spring.application.DistributionCenter.AppModelDto;

public class DcDto {
        private long dcNumber;
        private String dcCity;
        private String dcType;
        public long getDcNumber() {
            return dcNumber;
        }

        public void setDcNumber(long dcNumber) {
            this.dcNumber = dcNumber;
        }

        public String getDcCity() {
            return dcCity;
        }

        public void setDcCity(String dcCity) {
            this.dcCity = dcCity;
        }

        public String getDcType() {
            return dcType;
        }

        public void setDcType(String dcType) {
            this.dcType = dcType;
        }

        public DcDto(long dcNumber, String dcCity, String dcType) {
            this.dcNumber = dcNumber;
            this.dcCity = dcCity;
            this.dcType = dcType;
        }

        public DcDto() {
        }
    }
