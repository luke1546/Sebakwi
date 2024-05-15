import imageio

def create_gif(frames) -> None:
    gif_path = r"./"
    imageio.mimsave(gif_path, frames, fps=2)  # fps 조정 가능
    custom_log_info("create gif" + str(rc))