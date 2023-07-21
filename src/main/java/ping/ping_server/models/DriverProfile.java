package ping.ping_server.models;

import javax.persistence.*;

@Entity
public class DriverProfile {
    @Id
    @Column(name = "profile_id")
    private Long id;
    private String licensePlate;
    private String driverLicenseFrontUrl;
    private String driverLicenseBackUrl;
    private String carInsurance;
    private String citizenIdentificationNumber;
    private String citizenIdentificationFrontUrl;
    private String citizenIdentificationBackUrl;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
}
