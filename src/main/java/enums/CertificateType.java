package enums;

public enum CertificateType {
    CustomCertificate("Custom Certificate"),
    TransferCertificate("Transfer Certificate"),
    PossessionCertificate("Possession Certificate"),
    ExportCertificate("Export Certifcate");

    public final String certificate;

    public String Certificate() {
        return certificate;
    }

    @Override
    public String toString() {
        return certificate;
    }

    CertificateType(String certificate) {
        this.certificate = certificate;
    }
}

